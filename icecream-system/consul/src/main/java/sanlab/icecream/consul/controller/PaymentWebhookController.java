package sanlab.icecream.consul.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.dto.payment.PaymentWebhookRequestDto;
import sanlab.icecream.consul.dto.payment.PaymentWebhookResponseDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.service.OrderService;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PAYMENT_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_GARDENER;

@Slf4j
@RestController
@RequestMapping("/webhook/payment")
@RequiredArgsConstructor
@PreAuthorize(HAS_ROLE_GARDENER)
public class PaymentWebhookController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<PaymentWebhookResponseDto> confirmPayment(@RequestBody PaymentWebhookRequestDto payload) {
        try {
            var result = orderService.confirmPayment(payload);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch(error) {
                case REPOSITORY_PAYMENT_NOT_FOUND -> new HttpNotFoundException(ex);
                case REPOSITORY_PERSIST_DATA_FAILED -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
