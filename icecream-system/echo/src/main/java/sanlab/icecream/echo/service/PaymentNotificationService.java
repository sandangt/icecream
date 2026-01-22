package sanlab.icecream.echo.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sanlab.icecream.echo.model.BellNotificationMessage;
import sanlab.icecream.echo.repository.crud.BellNotificationMessageRepository;
import sanlab.icecream.echo.repository.mail.EMailRepository;
import sanlab.icecream.echo.repository.ws.WSRepository;
import sanlab.icecream.fundamentum.constant.EBellNotificationMessageType;
import sanlab.icecream.fundamentum.constant.EDeliveryMethod;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentMethod;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;
import sanlab.icecream.fundamentum.dto.PaymentNotificationDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.DatetimeUtils;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static sanlab.icecream.echo.constant.EMailInfo.PAYMENT_NOTIFICATION;
import static sanlab.icecream.echo.exception.EchoErrorModel.MAIL_SEND_EMAIL_FAILED;
import static sanlab.icecream.echo.exception.EchoErrorModel.SERVICE_NOTIFICATION_PAYLOAD_INVALID;
import static sanlab.icecream.fundamentum.utils.DatetimeUtils.PatternType.TYPE_1;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentNotificationService {

    private final EMailRepository emailRepository;
    private final BellNotificationMessageRepository notiRepository;
    private final WSRepository wsRepository;

    public void notifyPayment(PaymentNotificationDto payload) {
        sendSuccessfulPaymentEmail(payload);
    }

    private void sendSuccessfulPaymentEmail(PaymentNotificationDto payload) {
        var payloadOptional = Optional.ofNullable(payload);
        if (payloadOptional.map(PaymentNotificationDto::getEmail).filter(StringUtils::isNotEmpty).isEmpty()) {
            throw new IcRuntimeException(SERVICE_NOTIFICATION_PAYLOAD_INVALID, "Empty email from successful payment notification");
        }
        var variables = getVariableMap(payload);
        var notiMessage = buildNotiMessage(payload);
        try {
            emailRepository.sendToSingle(payload.getEmail(), PAYMENT_NOTIFICATION.getSubject(), PAYMENT_NOTIFICATION.getTemplatePath(), variables);
            var savedMessage = notiRepository.save(notiMessage);
            wsRepository.sendFENotificationByEmail(payload.getEmail(), Collections.singletonList(savedMessage));
        } catch (MessagingException ex) {
            LogUtils.logException(log, ex);
            throw new IcRuntimeException(MAIL_SEND_EMAIL_FAILED, "Failed to send email for successful payment notification");
        }
    }

    private static Map<String, String> getVariableMap(PaymentNotificationDto payload) {
        var payloadOptional = Optional.ofNullable(payload);
        Map<String, String> args = new HashMap<>();
        payloadOptional.map(PaymentNotificationDto::getFirstName).ifPresent(inner -> args.put("firstName", inner));
        payloadOptional.map(PaymentNotificationDto::getLastName).ifPresent(inner -> args.put("lastName", inner));
        payloadOptional.map(PaymentNotificationDto::getAmount).ifPresent(inner -> args.put("amount", inner));
        payloadOptional.map(PaymentNotificationDto::getOrderId).ifPresent(inner -> args.put("orderId", inner.toString()));
        payloadOptional.map(PaymentNotificationDto::getDeliveryFee).ifPresent(inner -> args.put("deliveryFee", inner));

        payloadOptional.map(PaymentNotificationDto::getTime).ifPresent(inner ->
            args.put("time", DatetimeUtils.toFormalStringUTC(inner, TYPE_1))
        );

        payloadOptional.map(PaymentNotificationDto::getDeliveryMethod)
            .ifPresent(inner -> args.put("deliveryMethod", EDeliveryMethod.toFormalText(inner)));
        payloadOptional.map(PaymentNotificationDto::getPaymentMethod)
            .ifPresent(inner -> args.put("paymentMethod", EPaymentMethod.toFormalText(inner)));
        payloadOptional.map(PaymentNotificationDto::getPaymentStatus)
            .ifPresent(inner -> args.put("paymentStatus", EPaymentStatus.toFormalText(inner)));
        payloadOptional.map(PaymentNotificationDto::getOrderStatus)
            .ifPresent(inner -> args.put("orderStatus", EOrderStatus.toFormalText(inner)));

        return args;
    }

    private static BellNotificationMessage buildNotiMessage(PaymentNotificationDto payload) {
        return BellNotificationMessage.builder()
            .email(payload.getEmail())
            .seen(false)
            .title("Payment completed")
            .content("You paid %s for this".formatted(payload.getAmount()))
            .type(EBellNotificationMessageType.PAYMENT.name())
            .build();
    }
}
