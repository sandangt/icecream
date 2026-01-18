package sanlab.icecream.echo.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import sanlab.icecream.echo.service.PaymentNotificationService;
import sanlab.icecream.fundamentum.dto.PaymentNotificationDto;

import java.util.function.Consumer;

@Configuration
public class PaymentNotificationConsumer {

    private final PaymentNotificationService paymentService;

    public PaymentNotificationConsumer(PaymentNotificationService paymentNotificationService) {
        this.paymentService = paymentNotificationService;
    }

    @Bean
    public Consumer<Message<PaymentNotificationDto>> notifyPayment() {
        return message -> {
            PaymentNotificationDto payload = message.getPayload();
            paymentService.notifyPayment(payload);
        };
    }

}
