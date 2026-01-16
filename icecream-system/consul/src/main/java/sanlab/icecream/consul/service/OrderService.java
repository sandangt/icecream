package sanlab.icecream.consul.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import sanlab.icecream.consul.dto.extended.OrderExtendedDto;
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
import sanlab.icecream.consul.repository.queue.PaymentNotificationQueueRepository;
import sanlab.icecream.consul.repository.restclient.PaymentRepository;
import sanlab.icecream.consul.viewmodel.request.OrderRequest;
import sanlab.icecream.fundamentum.constant.EDeliveryStatus;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.LogUtils;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static sanlab.icecream.consul.exception.ConsulErrorModel.ADDRESS_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.HASH_ALGORITHM_EXCEPTION;
import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_PAYMENT_WEBHOOK_SIGNATURE;

@Service
@Slf4j
public class OrderService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentNotificationQueueRepository paymentNotiRepository;
    private final OrderMapper orderMapper;
    private final String webhookSecret;

    public OrderService(
        ProductRepository productRepository,
        CustomerRepository customerRepository,
        AddressRepository addressRepository,
        OrderRepository orderRepository,
        OrderItemRepository orderItemRepository,
        PaymentRepository paymentRepository,
        PaymentNotificationQueueRepository paymentNotiRepository,
        OrderMapper orderMapper,
        @Value("${app.payment.webhook.secret}") String webhookSecret
    ) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.paymentRepository = paymentRepository;
        this.paymentNotiRepository = paymentNotiRepository;
        this.orderMapper = orderMapper;
        this.webhookSecret = webhookSecret;
    }

    @Transactional(readOnly = true)
    public OrderExtendedDto get(UUID id) {
        return orderRepository.findFirstByCustomer_UserId(id)
            .map(orderMapper::entityToExtendedDto)
            .orElse(null);
    }

    @Transactional
    public OrderExtendedDto create(UUID userId, OrderRequest payload) {
        Customer customer = customerRepository.findFirstByUserId(userId)
            .orElseThrow(() -> new IcRuntimeException(CUSTOMER_NOT_FOUND, "id: %s".formatted(userId)));
        Address address = addressRepository.findFirstById(payload.getAddressId())
            .orElseThrow(() -> new IcRuntimeException(ADDRESS_NOT_FOUND, "id: %s".formatted(payload.getAddressId())));
        var requestMapByProductIds = payload.getOrderItems()
            .stream()
            .collect(Collectors.toMap(OrderRequest.OrderItemRequest::getProductId, Function.identity()));
        Map<UUID, Product> productMap = productRepository.findAllByIdIn(requestMapByProductIds.keySet().stream().toList())
            .stream().collect(Collectors.toMap(Product::getId, Function.identity()));
        Order savedOrder = orderRepository.save(Order.builder()
            .customer(customer)
            .address(address)
            .note(payload.getNote())
            .discount(payload.getDiscount())
            .totalPrice(payload.getTotalPrice())
            .deliveryFee(payload.getDeliveryFee())
            .deliveryMethod(payload.getDeliveryMethod())
            .paymentMethod(payload.getPaymentMethod())
            .deliveryStatus(EDeliveryStatus.PREPARING.name())
            .orderStatus(EOrderStatus.PENDING.name())
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
        savedOrder.setOrderItems(orderItems);
        Double amount = savedOrder.getTotalPrice() + savedOrder.getDeliveryFee();
//        paymentRepository.createInvoice(CreateInvoiceRequestDto.builder()
//                .amount(amount)
//                .reference()
//                .webhookUrl()
//                .redirectUrl()
//                .build());
        return orderMapper.entityToExtendedDto(savedOrder);
    }

    public PaymentWebhookResponseDto paymentConfirm(HttpServletRequest req) throws IOException {
        String payloadStr = IOUtils.toString(req.getReader());
        String signature = req.getHeader("X-Signature");
        if (!isPaymentSignatureValid(payloadStr, signature, this.webhookSecret)) {
            throw new IcRuntimeException(INVALID_PAYMENT_WEBHOOK_SIGNATURE);
        }
        ObjectMapper mapper = new ObjectMapper();
        var payload = mapper.readValue(payloadStr, PaymentWebhookRequestDto.class);
        paymentNotiRepository.success();
        return new PaymentWebhookResponseDto();
    }

    private static final String HMAC_SHA256 = "HmacSHA256";
    private static boolean isPaymentSignatureValid(String payload, String signature, String secret) {
        try {
            var mac = Mac.getInstance(HMAC_SHA256);
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256));
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String expected = HexFormat.of().formatHex(hash);
            return MessageDigest.isEqual(
                expected.getBytes(StandardCharsets.UTF_8),
                signature.getBytes(StandardCharsets.UTF_8)
            );
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            LogUtils.logException(log, ex);
            throw new IcRuntimeException(ex, HASH_ALGORITHM_EXCEPTION);
        }

    }

}
