package sanlab.icecream.echo.repository.mail.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sanlab.icecream.echo.repository.mail.EMailRepository;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class EMailRepositoryImpl implements EMailRepository {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;
    private final String fromEmail;
    private final String fromName;

    public EMailRepositoryImpl(TemplateEngine templateEngine,
                               @Value("${app.mail.from}") String fromEmail,
                               @Value("${app.mail.from-name}") String fromName,
                                JavaMailSender mailSender) {
        this.fromEmail = fromEmail;
        this.fromName = fromName;
        this.templateEngine = templateEngine;
        this.mailSender = mailSender;
    }

    @Override
    public void sendToSingle(String recipient, String subject, String templatePath, Map<String, String> variables) throws MessagingException {
        sendHtmlEmail(Collections.singletonList(recipient), subject, templatePath, variables);
    }

    @Override
    public void sendToMultiple(List<String> recipients, String subject, String templatePath, Map<String, String> variables) throws MessagingException {
        sendHtmlEmail(recipients, subject, templatePath, variables);
    }

    private void sendHtmlEmail(List<String> recipients, String subject, String templatePath, Map<String, String> variables) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
        helper.setFrom(fromName + " <" + fromEmail + ">");
        helper.setTo(recipients.toArray(new String[0]));
        helper.setSubject(subject);
        Context context = new Context();
        variables.forEach(context::setVariable);
        String htmlContent = templateEngine.process(templatePath, context);
        helper.setText(htmlContent, true);
        mailSender.send(mimeMessage);
    }
}
