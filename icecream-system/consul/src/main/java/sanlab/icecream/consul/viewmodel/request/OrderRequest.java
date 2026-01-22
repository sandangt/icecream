package sanlab.icecream.consul.viewmodel.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sanlab.icecream.fundamentum.utils.PriceCalculationUtils;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String note;
    private Double discount;
    private Double totalPrice;
    private Double deliveryFee;
    private String deliveryMethod;
    private String paymentMethod;
    private List<OrderItemRequest> orderItems;
    private UUID addressId;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemRequest {
        private UUID productId;
        private Long quantity;
        private String note;
        private Double discount;
        private Double price;

        public Double getFinalPrice() {
            return PriceCalculationUtils.discountedPrice(price, discount) * quantity;
        }


    }

    public List<OrderItemRequest> getOrderItems() {
        var map = orderItems.stream().collect(Collectors.toMap(
            OrderRequest.OrderItemRequest::getProductId, Function.identity(),
            (item1, item2) -> OrderItemRequest.builder()
                .productId(item1.getProductId())
                .quantity(item1.getQuantity() + item2.getQuantity())
                .note(item1.getNote())
                .discount(item1.getDiscount())
                .price(item1.getPrice())
                .build()
        ));
        return map.values().stream().toList();
    }

    public Double getTotalPrice() {
        return getOrderItems().stream().mapToDouble(OrderItemRequest::getFinalPrice).sum();
    }

}
