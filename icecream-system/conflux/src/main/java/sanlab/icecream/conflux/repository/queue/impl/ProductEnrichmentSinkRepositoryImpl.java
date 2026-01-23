package sanlab.icecream.conflux.repository.queue.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sanlab.icecream.conflux.model.enriched.ProductEnriched;
import sanlab.icecream.conflux.repository.queue.ProductEnrichmentSinkRepository;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ProductEnrichmentSinkRepositoryImpl implements ProductEnrichmentSinkRepository {

    private final StreamBridge streamBridge;

    private static final String PRODUCT_ENRICHMENT_SINK_OUT_CHANNEL = "enrichProduct-out-0";

    @Override
    public void produce(ProductEnriched payload) {
        var message = MessageBuilder
            .withPayload(payload)
            .setHeader(KafkaHeaders.KEY, Collections.singletonMap("id", payload.getId()))
            .build();
        streamBridge.send(PRODUCT_ENRICHMENT_SINK_OUT_CHANNEL, message);
    }
}
