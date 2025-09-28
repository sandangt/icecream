package sanlab.icecream.consul.viewmodel.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private UUID id;
    private List<CartItemRequest> cartItems;

    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CartItemRequest {
        private UUID productId;
        private Long quantity;
    }

    public List<CartItemRequest> getCartItems() {
        var map = cartItems.stream().collect(Collectors.toMap(
            CartRequest.CartItemRequest::getProductId, Function.identity(),
            (item1, item2) -> new CartItemRequest(item1.getProductId(), item1.getQuantity() + item2.getQuantity())
        ));
        return map.values().stream().toList();
    }

}
