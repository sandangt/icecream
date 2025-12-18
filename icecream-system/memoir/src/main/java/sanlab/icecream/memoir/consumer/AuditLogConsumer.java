package sanlab.icecream.memoir.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import sanlab.icecream.fundamentum.dto.core.AuditLogDto;
import sanlab.icecream.memoir.service.AuditLogService;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class AuditLogConsumer {

    private final AuditLogService auditLogService;

    @Bean(name = "auditLog")
    public Consumer<Message<AuditLogDto>> receive() {
        return message -> {
            var payload = message.getPayload();
            auditLogService.save(payload);
        };
    }

}
