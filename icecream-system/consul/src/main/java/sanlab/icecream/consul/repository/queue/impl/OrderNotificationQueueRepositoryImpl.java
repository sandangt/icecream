package sanlab.icecream.consul.repository.queue.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.queue.OrderNotificationQueueRepository;
import sanlab.icecream.fundamentum.dto.CheckoutNotificationDto;
import sanlab.icecream.fundamentum.dto.PaymentNotificationDto;

@Component
@RequiredArgsConstructor
public class OrderNotificationQueueRepositoryImpl implements OrderNotificationQueueRepository {

    private final StreamBridge streamBridge;

    private static final String PAYMENT_NOTIFICATION_OUT_CHANNEL = "notifyPayment-out-0";
    private static final String CHECKOUT_NOTIFICATION_OUT_CHANNEL = "notifyCheckout-out-0";

    @Override
    public void notifyPayment(PaymentNotificationDto payload) {
        var message = MessageBuilder
            .withPayload(payload)
            .build();
        streamBridge.send(PAYMENT_NOTIFICATION_OUT_CHANNEL, message);
    }

    @Override
    public void notifyCheckout(CheckoutNotificationDto payload) {
        var message = MessageBuilder
            .withPayload(payload)
            .build();
        streamBridge.send(CHECKOUT_NOTIFICATION_OUT_CHANNEL, message);
    }

}
