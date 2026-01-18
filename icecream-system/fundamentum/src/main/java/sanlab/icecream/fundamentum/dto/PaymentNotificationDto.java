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
public class PaymentNotificationDto {

    private UUID userId;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String amount;
    private Long time;
    private UUID orderId;
    private String deliveryFee;
    private String deliveryMethod;
    private String paymentMethod;
    private String paymentStatus;
    private String orderStatus;

}
