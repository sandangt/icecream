package sanlab.icecream.memoir.consumer;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import sanlab.icecream.fundamentum.dto.AuditLogDto;

import java.util.function.Consumer;

@Component("auditlog")
public class AuditLogConsumer implements Consumer<Message<AuditLogDto>> {

    @Override
    public void accept(Message<AuditLogDto> message) {
        var payload = message.getPayload();
        System.out.println(payload);
    }

}
