package sanlab.icecream.consul.dto.payment;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentWebhookRequestDto(
    @JsonProperty("payment_id") String paymentId,
    String reference, Double amount, String status, String timestamp
) {}
