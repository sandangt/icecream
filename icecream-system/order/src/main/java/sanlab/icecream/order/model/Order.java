package sanlab.icecream.order.model;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanlab.icecream.sharedlib.enumeration.EDeliveryMethod;
import sanlab.icecream.sharedlib.enumeration.EDeliveryStatus;
import sanlab.icecream.sharedlib.enumeration.EOrderStatus;
import sanlab.icecream.sharedlib.abstractentity.AbstractAuditEntity;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Order")
@Table(name = "order")
public class Order extends AbstractAuditEntity {

    @Id
    @SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_sequence")
    private Long id;

    private String email;
    private String note;
    private BigDecimal totalPrice;
    private BigDecimal deliveryFee;
    private EOrderStatus orderStatus;
    private EDeliveryMethod deliveryMethod;
    private EDeliveryStatus deliveryStatus;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<OrderItem> orderItemList;

    private Long customerId;
    private String contactName;
    private String phone;
    private Long paymentId;
    private Long addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        return id != null && id.equals(((Order) o).id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
