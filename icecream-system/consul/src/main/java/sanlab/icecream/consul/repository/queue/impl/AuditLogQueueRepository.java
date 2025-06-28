package sanlab.icecream.consul.repository.queue.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.queue.IAuditLogQueueRepository;
import sanlab.icecream.fundamentum.dto.AuditLogDto;

@Component
@RequiredArgsConstructor
public class AuditLogQueueRepository implements IAuditLogQueueRepository {

    private final StreamBridge streamBridge;

    private static final String OUT_CHANNEL = "auditlog-out-0";

    @Override
    public void send() {
        var payload = AuditLogDto.builder()
            .email("")
            .content("Hello World")
            .build();
        Message<AuditLogDto> message = MessageBuilder
            .withPayload(payload)
            .build();
        streamBridge.send(OUT_CHANNEL, message);
    }
}
