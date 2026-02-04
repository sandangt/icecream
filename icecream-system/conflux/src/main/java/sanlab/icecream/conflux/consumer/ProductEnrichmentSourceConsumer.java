package sanlab.icecream.conflux.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.Message;
import sanlab.icecream.conflux.service.ProductEnrichmentService;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.io.IOException;
import java.util.function.Consumer;

import static sanlab.icecream.conflux.exception.ConfluxErrorModel.CONSUMER_PARSING_ENRICHED_PRODUCT_FAILED;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ProductEnrichmentSourceConsumer {

    private final ProductEnrichmentService productService;
    private final ObjectMapper objectMapper;

    @Bean
    public Consumer<Message<Object>> enrichProduct() {
        return message -> {
            Object payload = message.getPayload();
            if (payload instanceof KafkaNull) {
                LogUtils.logInfo(log, "Received tombstone record (KafkaNull), skipping");
                return;
            }
            String topic = message.getHeaders().get(KafkaHeaders.RECEIVED_TOPIC, String.class);
            try {
                JsonNode jsonNode = switch (payload) {
                    case JsonNode node -> node;
                    case String s -> objectMapper.readTree(s);
                    case byte[] bytes -> objectMapper.readTree(bytes);
                    default -> objectMapper.valueToTree(payload);
                };
                productService.process(topic, jsonNode);
            } catch (IOException e) {
                throw new IcRuntimeException(CONSUMER_PARSING_ENRICHED_PRODUCT_FAILED, "Topic: %s".formatted(topic));
            }
        };
    }

}
