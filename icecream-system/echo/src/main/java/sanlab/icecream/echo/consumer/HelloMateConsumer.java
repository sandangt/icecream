package sanlab.icecream.echo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@Slf4j
@Configuration
public class HelloMateConsumer {

    @Bean
    public Consumer<Message<String>> helloRabbit() {
        return message -> {
            log.info("Received message: {}", message.getPayload());
        };
    }

}
