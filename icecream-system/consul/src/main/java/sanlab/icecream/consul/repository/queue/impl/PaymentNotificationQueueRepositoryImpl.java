package sanlab.icecream.consul.repository.queue.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import sanlab.icecream.consul.repository.queue.PaymentNotificationQueueRepository;

@Component
@RequiredArgsConstructor
public class PaymentNotificationQueueRepositoryImpl implements PaymentNotificationQueueRepository {

    private final StreamBridge streamBridge;

    private static final String PAYMENT_SUCCESS_OUT_CHANNEL = "paymentSuccess-out-0";

    @Override
    public void success() {

    }
}
