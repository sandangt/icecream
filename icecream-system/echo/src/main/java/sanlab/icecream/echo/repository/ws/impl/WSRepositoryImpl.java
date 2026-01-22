package sanlab.icecream.echo.repository.ws.impl;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import sanlab.icecream.echo.model.BellNotificationMessage;
import sanlab.icecream.echo.repository.ws.WSRepository;

import java.util.List;
import java.util.UUID;

@Component
public class WSRepositoryImpl implements WSRepository {

    private static final String FE_NOTIFICATION_DESTINATION = "/topic/notification";

    private final SimpMessagingTemplate messagingTemplate;

    public WSRepositoryImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void sendFENotificationByUserId(UUID userId, List<String> messages) {
        messagingTemplate.convertAndSendToUser(userId.toString(), FE_NOTIFICATION_DESTINATION, messages);
    }

    @Override
    public void sendFENotificationByEmail(String email, List<BellNotificationMessage> messages) {
        messagingTemplate.convertAndSendToUser(email, FE_NOTIFICATION_DESTINATION, messages);
    }
}
