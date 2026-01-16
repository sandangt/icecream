package sanlab.icecream.consul.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.dto.payment.PaymentWebhookResponseDto;
import sanlab.icecream.consul.exception.HttpBadRequestException;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.service.OrderService;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.io.IOException;

import static sanlab.icecream.consul.exception.ConsulErrorModel.HASH_ALGORITHM_EXCEPTION;
import static sanlab.icecream.consul.exception.ConsulErrorModel.INVALID_PAYMENT_WEBHOOK_SIGNATURE;

@Slf4j
@RestController
@RequestMapping("/webhook/payment")
@RequiredArgsConstructor
public class PaymentWebhookController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<PaymentWebhookResponseDto> paymentConfirm(HttpServletRequest req) throws IOException {
        try {
            var result = orderService.paymentConfirm(req);
            return ResponseEntity.ok(new PaymentWebhookResponseDto());
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch(error) {
                case INVALID_PAYMENT_WEBHOOK_SIGNATURE, HASH_ALGORITHM_EXCEPTION -> new HttpBadRequestException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
