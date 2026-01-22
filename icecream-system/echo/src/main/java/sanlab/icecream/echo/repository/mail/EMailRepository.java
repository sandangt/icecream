package sanlab.icecream.echo.repository.mail;

import jakarta.mail.MessagingException;

import java.util.List;
import java.util.Map;

public interface EMailRepository {
    void sendToSingle(String recipient, String subject, String templatePath, Map<String, String> variables) throws MessagingException;
    void sendToMultiple(List<String> recipients, String subject, String templatePath, Map<String, String> variables) throws MessagingException;
}
