package sanlab.icecream.consul.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sanlab.icecream.consul.dto.extended.OrderExtendedDto;
import sanlab.icecream.consul.dto.payment.CreateInvoiceRequestDto;
import sanlab.icecream.consul.dto.payment.PaymentWebhookRequestDto;
import sanlab.icecream.consul.dto.payment.PaymentWebhookResponseDto;
import sanlab.icecream.consul.mapper.OrderMapper;
import sanlab.icecream.consul.model.Address;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.model.Order;
import sanlab.icecream.consul.model.OrderItem;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.repository.crud.AddressRepository;
import sanlab.icecream.consul.repository.crud.CustomerRepository;
import sanlab.icecream.consul.repository.crud.OrderItemRepository;
import sanlab.icecream.consul.repository.crud.OrderRepository;
import sanlab.icecream.consul.repository.crud.ProductRepository;
import sanlab.icecream.consul.repository.queue.OrderNotificationQueueRepository;
import sanlab.icecream.consul.repository.restclient.PaymentRepository;
import sanlab.icecream.consul.viewmodel.request.OrderRequest;
import sanlab.icecream.consul.viewmodel.response.CreateOrderResponse;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;
import sanlab.icecream.fundamentum.dto.CheckoutNotificationDto;
import sanlab.icecream.fundamentum.dto.PaymentNotificationDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.PathUtils;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_ADDRESS_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PAYMENT_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.fundamentum.constant.EPaymentMethod.COD;

@Service
@Slf4j
public class OrderService {

    private final CartService cartService;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;
    private final OrderNotificationQueueRepository orderNotiRepository;
    private final OrderMapper orderMapper;
    private final String webhookHost;
    private final String storefrontUrl;

    public OrderService(
        CartService cartService,
        ProductRepository productRepository,
        CustomerRepository customerRepository,
        AddressRepository addressRepository,
        OrderRepository orderRepository,
        OrderItemRepository orderItemRepository,
        PaymentRepository paymentRepository,
        OrderNotificationQueueRepository orderNotiRepository,
        OrderMapper orderMapper,
        @Value("${app.storefront.url}") String storefrontUrl,
        @Value("${app.payment.webhook.host}") String webhookHost
    ) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepository;
        this.orderNotiRepository = orderNotiRepository;
        this.cartService = cartService;
        this.orderMapper = orderMapper;
        this.storefrontUrl = storefrontUrl;
        this.webhookHost = webhookHost;
    }

    @Transactional(readOnly = true)
    public OrderExtendedDto get(UUID id) {
        return orderRepository.findFirstByCustomer_UserId(id)
            .map(orderMapper::entityToExtendedDto)
            .orElse(null);
    }

    @Transactional
    public CreateOrderResponse create(UUID userId, OrderRequest payload) {
        Customer customer = customerRepository.findFirstByUserId(userId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CUSTOMER_NOT_FOUND, "id: %s".formatted(userId)));
        Address address = addressRepository.findFirstById(payload.getAddressId())
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_ADDRESS_NOT_FOUND, "id: %s".formatted(payload.getAddressId())));
        var requestMapByProductIds = payload.getOrderItems()
            .stream()
            .collect(Collectors.toMap(OrderRequest.OrderItemRequest::getProductId, Function.identity()));
        Map<UUID, Product> productMap = productRepository.findAllByIdIn(requestMapByProductIds.keySet().stream().toList())
            .stream().collect(Collectors.toMap(Product::getId, Function.identity()));
        Order savedOrder;
        try {
            savedOrder = orderRepository.save(Order.builder()
                .customer(customer)
                .address(address)
                .note(payload.getNote())
                .discount(payload.getDiscount())
                .totalPrice(payload.getTotalPrice())
                .deliveryFee(payload.getDeliveryFee())
                .deliveryMethod(payload.getDeliveryMethod())
                .paymentMethod(payload.getPaymentMethod())
                .orderStatus(EOrderStatus.ACCEPTED.name())
                .paymentStatus(EPaymentStatus.PENDING.name())
                .build());
            List<OrderItem> orderItems = requestMapByProductIds.values().stream()
                .map(inner -> {
                    Product product = productMap.get(inner.getProductId());
                    return (OrderItem) OrderItem.builder()
                        .quantity(inner.getQuantity())
                        .note(inner.getNote())
                        .price(inner.getPrice())
                        .discount(inner.getDiscount())
                        .product(product)
                        .order(savedOrder)
                        .build();
                })
                .toList();
            orderItemRepository.saveAll(orderItems);
            cartService.reset(userId);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "Persistent error happen when creating order");
        }
        Long finalPrice = Math.round(savedOrder.getFinalPrice());
        String paymentUrl = null;
        if (!COD.name().equals(savedOrder.getPaymentMethod())) {
            var invoiceResp = paymentRepository.createInvoice(CreateInvoiceRequestDto.builder()
                    .amount(finalPrice)
                    .reference(savedOrder.getId().toString())
                    .webhookUrl(buildWebhookUrl())
                    .redirectUrl(this.storefrontUrl)
                    .build());
            String paymentId = PathUtils.getLastPathSegment(invoiceResp.getPageUrl());
            try {
                orderRepository.updatePaymentId(savedOrder.getId(), UUID.fromString(paymentId));
            } catch (Exception ex) {
                throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "Failed to update payment id for order table");
            }
            paymentUrl = invoiceResp.getPageUrl();
        }
        orderNotiRepository.notifyCheckout(CheckoutNotificationDto.builder()
            .userId(customer.getUserId())
            .email(customer.getEmail())
            .username(customer.getUsername())
            .firstName(customer.getFirstName())
            .lastName(customer.getLastName())
            .orderId(savedOrder.getId())
            .amount(String.valueOf(finalPrice))
            .deliveryMethod(savedOrder.getDeliveryMethod())
            .paymentMethod(savedOrder.getPaymentMethod())
            .paymentStatus(savedOrder.getPaymentStatus())
            .orderStatus(savedOrder.getOrderStatus())
            .checkoutTime(Instant.now().toEpochMilli())
            .build());
        return new CreateOrderResponse(paymentUrl, EOrderStatus.ACCEPTED.name());
    }

    @Transactional
    public PaymentWebhookResponseDto confirmPayment(PaymentWebhookRequestDto payload) {
        UUID paymentId = UUID.fromString(payload.getPaymentId());
        Order order = orderRepository.findFirstByPaymentId(paymentId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_PAYMENT_NOT_FOUND));
        if (!EOrderStatus.isActiveStatus(order.getOrderStatus())) {
            return new PaymentWebhookResponseDto("expired", "Order has been expired");
        }
        try {
            orderRepository.updatePaymentStatusWhenEPaid(order.getId());
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "Failed to update order status after customer paid");
        }
        Customer customer = order.getCustomer();
        orderNotiRepository.notifyPayment(PaymentNotificationDto.builder()
            .userId(customer.getUserId())
            .username(customer.getUsername())
            .email(customer.getEmail())
            .firstName(customer.getFirstName())
            .lastName(customer.getLastName())
            .amount(payload.getAmount())
            .time(Instant.now().toEpochMilli())
            .orderId(order.getId())
            .deliveryMethod(order.getDeliveryMethod())
            .deliveryMethod(order.getDeliveryMethod())
            .deliveryFee(String.valueOf(order.getDeliveryFee()))
            .paymentMethod(order.getPaymentMethod())
            .paymentStatus(order.getPaymentStatus())
            .orderStatus(order.getOrderStatus())
            .build());
        return new PaymentWebhookResponseDto(
            "success", "Order ID %s\nPayment ID %s".formatted(order.getId(), paymentId));
    }

    private String buildWebhookUrl() {
        String url = getBaseUrl() + "/webhook/payment";
        return url.replace("localhost", webhookHost);
    }

    private static String getBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

}
