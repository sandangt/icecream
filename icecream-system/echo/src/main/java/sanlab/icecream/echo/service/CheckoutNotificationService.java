package sanlab.icecream.echo.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import sanlab.icecream.echo.repository.mail.EMailRepository;
import sanlab.icecream.fundamentum.constant.EDeliveryMethod;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentMethod;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;
import sanlab.icecream.fundamentum.dto.CheckoutNotificationDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;
import sanlab.icecream.fundamentum.utils.DatetimeUtils;
import sanlab.icecream.fundamentum.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static sanlab.icecream.echo.constant.EMailInfo.CHECKOUT_NOTIFICATION;
import static sanlab.icecream.echo.exception.EchoErrorModel.MAIL_SEND_EMAIL_FAILED;
import static sanlab.icecream.echo.exception.EchoErrorModel.SERVICE_NOTIFICATION_PAYLOAD_INVALID;
import static sanlab.icecream.fundamentum.utils.DatetimeUtils.PatternType.TYPE_1;

@Service
@RequiredArgsConstructor
@Slf4j
public class CheckoutNotificationService {

    private final EMailRepository emailRepository;


    public void notifyCheckout(CheckoutNotificationDto payload) {
        sendCheckoutSummaryEmail(payload);
    }

    private void sendCheckoutSummaryEmail(CheckoutNotificationDto payload) {
        var payloadOptional = Optional.ofNullable(payload);
        if (payloadOptional.map(CheckoutNotificationDto::getEmail).filter(StringUtils::isNotEmpty).isEmpty()) {
            throw new IcRuntimeException(SERVICE_NOTIFICATION_PAYLOAD_INVALID, "Empty email from checkout summary notification");
        }
        var variables = getVariableMap(payload);
        try {
            emailRepository.sendToSingle(payload.getEmail(), CHECKOUT_NOTIFICATION.getSubject(), CHECKOUT_NOTIFICATION.getTemplatePath(), variables);
        } catch (MessagingException ex) {
            LogUtils.logException(log, ex);
            throw new IcRuntimeException(MAIL_SEND_EMAIL_FAILED, "Failed to send email for checkout summary notification");
        }

    }

    private Map<String, String>  getVariableMap(CheckoutNotificationDto payload) {
        var payloadOptional = Optional.ofNullable(payload);
        Map<String, String> variables = new HashMap<>();

        payloadOptional.map(CheckoutNotificationDto::getFirstName).ifPresent(inner -> variables.put("firstName", inner));
        payloadOptional.map(CheckoutNotificationDto::getLastName).ifPresent(inner -> variables.put("lastName", inner));
        payloadOptional.map(CheckoutNotificationDto::getAmount).ifPresent(inner -> variables.put("amount", inner));
        payloadOptional.map(CheckoutNotificationDto::getOrderId).ifPresent(inner -> variables.put("orderId", inner.toString()));
        payloadOptional.map(CheckoutNotificationDto::getEstimatedDeliveryDate).ifPresent(inner -> variables.put("estimatedDeliveryDate", inner));

        payloadOptional.map(CheckoutNotificationDto::getCheckoutTime).ifPresent(inner ->
            variables.put("checkoutTime", DatetimeUtils.toFormalStringUTC(inner, TYPE_1))
        );

        payloadOptional.map(CheckoutNotificationDto::getDeliveryMethod)
            .ifPresent(inner -> variables.put("deliveryMethod", EDeliveryMethod.toFormalText(inner)));
        payloadOptional.map(CheckoutNotificationDto::getPaymentMethod)
            .ifPresent(inner -> variables.put("paymentMethod", EPaymentMethod.toFormalText(inner)));
        payloadOptional.map(CheckoutNotificationDto::getPaymentStatus)
            .ifPresent(inner -> variables.put("paymentStatus", EPaymentStatus.toFormalText(inner)));
        payloadOptional.map(CheckoutNotificationDto::getOrderStatus)
            .ifPresent(inner -> variables.put("orderStatus", EOrderStatus.toFormalText(inner)));

        return variables;
    }



}
