package sanlab.icecream.conflux.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import sanlab.icecream.conflux.service.ProductEnrichmentService;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ProductEnrichmentSourceConsumer {

    private final ProductEnrichmentService productService;

    @Bean
    public Consumer<Message<JsonNode>> enrichProduct() {
        return message -> {
            JsonNode payload = message.getPayload();
            String topic = message.getHeaders().get(KafkaHeaders.RECEIVED_TOPIC, String.class);
            productService.process(topic, payload);
        };
    }

}
