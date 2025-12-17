package sanlab.icecream.memoir.consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import sanlab.icecream.fundamentum.dto.core.AuditLogDto;

import java.util.function.Consumer;

@Configuration
public class AuditLogConsumer {

    @Bean(name = "auditlog")
    public Consumer<Message<AuditLogDto>> receive() {
        return message -> {
            var payload = message.getPayload();
            System.out.println(payload);
        };
    }

}
