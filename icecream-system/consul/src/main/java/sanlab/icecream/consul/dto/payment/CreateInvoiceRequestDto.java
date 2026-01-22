package sanlab.icecream.consul.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequestDto {
    private Long amount;
    private String reference;
    private String webhookUrl;
    private String redirectUrl;
}
