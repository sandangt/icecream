package sanlab.icecream.fundamentum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutNotificationDto {

    private UUID userId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private UUID orderId;
    private String amount;
    private String deliveryMethod;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;
    private String estimatedDeliveryDate;
    private Long checkoutTime;

}
