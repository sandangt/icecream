package sanlab.icecream.consul.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentWebhookRequestDto {
    @JsonProperty("payment_id")
    private String paymentId;
    private String reference;
    private String amount;
    private String status;
    private String timestamp;
    @JsonProperty("card_mask")
    private String cardMask;
}
