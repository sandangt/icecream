package sanlab.icecream.consul.repository.queue.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.queue.HelloMateQueueRepository;

import java.util.Collections;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class HelloMateQueueRepositoryImpl implements HelloMateQueueRepository {

    private final StreamBridge streamBridge;

    private static final String KAFKA_OUT_CHANNEL = "helloKafka-out-0";
    private static final String RABBIT_OUT_CHANNEL = "helloRabbit-out-0";

    @Override
    public void sayHelloKafka() {
        var message = MessageBuilder
            .withPayload("Hello Kafka from Consul!")
            .setHeader(KafkaHeaders.KEY, StringUtils.EMPTY)
            .build();
        streamBridge.send(KAFKA_OUT_CHANNEL, message);
    }

    @Override
    public void sayHelloRabbit() {
        var message = MessageBuilder.withPayload("Hello Rabbit from Consul!").build();
        streamBridge.send(RABBIT_OUT_CHANNEL, message);
    }
}
