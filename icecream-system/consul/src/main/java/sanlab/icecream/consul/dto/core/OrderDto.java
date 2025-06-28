package sanlab.icecream.consul.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.fundamentum.constant.EDeliveryMethod;
import sanlab.icecream.fundamentum.constant.EDeliveryStatus;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentMethod;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    protected UUID id;
    protected String note;
    protected Double discount;
    protected Double totalPrice;
    protected Double deliveryFee;
    protected EDeliveryMethod deliveryMethod;
    protected EPaymentMethod paymentMethod;
    protected EOrderStatus orderStatus;
    protected EDeliveryStatus deliveryStatus;
    protected EPaymentStatus paymentStatus;
}
