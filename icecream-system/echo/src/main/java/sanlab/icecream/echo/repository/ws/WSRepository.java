package sanlab.icecream.echo.repository.ws;

import sanlab.icecream.echo.model.BellNotificationMessage;

import java.util.List;
import java.util.UUID;

public interface WSRepository {

    void sendFENotificationByUserId(UUID userId, List<String> messages);
    void sendFENotificationByEmail(String username, List<BellNotificationMessage> messages);

}
