package sanlab.icecream.consul.repository.queue;

import sanlab.icecream.fundamentum.dto.CheckoutNotificationDto;
import sanlab.icecream.fundamentum.dto.PaymentNotificationDto;

public interface OrderNotificationQueueRepository {

    void notifyPayment(PaymentNotificationDto payload);
    void notifyCheckout(CheckoutNotificationDto payload);

}
