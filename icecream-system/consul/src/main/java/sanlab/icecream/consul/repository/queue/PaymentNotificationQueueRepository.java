package sanlab.icecream.consul.repository.queue;

import sanlab.icecream.fundamentum.dto.PaymentNotificationDto;

public interface PaymentNotificationQueueRepository {

    void notifyPayment(PaymentNotificationDto payload);

}
