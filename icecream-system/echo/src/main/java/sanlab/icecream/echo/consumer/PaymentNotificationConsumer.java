package sanlab.icecream.echo.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Configuration
public class PaymentNotificationConsumer {

    @Bean
    public Consumer<Message<String>> paymentSuccess() {
        return message -> {

        };
    }

}
