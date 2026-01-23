package sanlab.icecream.consul.repository.crud;

import sanlab.icecream.fundamentum.constant.EOrderStatus;

import java.util.UUID;

public interface OrderExtendRepository {

    void updatePaymentId(UUID orderId, UUID paymentId);

    void updatePaymentStatusWhenEPaid(UUID orderId);

    void updateOrderStatus(UUID orderId, EOrderStatus orderStatus);

}
