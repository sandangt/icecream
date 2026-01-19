package sanlab.icecream.echo.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import sanlab.icecream.echo.service.CheckoutNotificationService;
import sanlab.icecream.echo.service.PaymentNotificationService;
import sanlab.icecream.fundamentum.dto.CheckoutNotificationDto;
import sanlab.icecream.fundamentum.dto.PaymentNotificationDto;

import java.util.function.Consumer;

@Configuration
public class OrderNotificationConsumer {

    private final PaymentNotificationService paymentService;
    private final CheckoutNotificationService checkoutService;


    public OrderNotificationConsumer(PaymentNotificationService paymentService,
                                     CheckoutNotificationService checkoutService) {
        this.paymentService = paymentService;
        this.checkoutService = checkoutService;
    }

    @Bean
    public Consumer<Message<PaymentNotificationDto>> notifyPayment() {
        return message -> {
            PaymentNotificationDto payload = message.getPayload();
            paymentService.notifyPayment(payload);
        };
    }

    @Bean
    public Consumer<Message<CheckoutNotificationDto>> notifyCheckout() {
        return message -> {
            CheckoutNotificationDto payload = message.getPayload();
            checkoutService.notifyCheckout(payload);
        };
    }

}
