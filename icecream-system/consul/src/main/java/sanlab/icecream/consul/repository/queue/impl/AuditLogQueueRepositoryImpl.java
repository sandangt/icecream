package sanlab.icecream.consul.repository.queue.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.queue.AuditLogQueueRepository;
import sanlab.icecream.fundamentum.dto.AuditLogDto;

@Component
@RequiredArgsConstructor
public class AuditLogQueueRepositoryImpl implements AuditLogQueueRepository {

    private final StreamBridge streamBridge;

    private static final String OUT_CHANNEL = "auditLog-out-0";

    @Override
    public void send() {
        var payload = AuditLogDto.builder()
            .email("")
            .content("Hello World")
            .build();
        Message<AuditLogDto> message = MessageBuilder
            .withPayload(payload)
            .setHeader(KafkaHeaders.KEY, StringUtils.EMPTY)
            .build();
        streamBridge.send(OUT_CHANNEL, message);
    }
}
