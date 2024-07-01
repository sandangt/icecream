package sanlab.icecream.frontier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "order_tbl")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Order extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String note;
    private Double discount;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "delivery_fee")
    private Double deliveryFee;
    @Column(name = "delivery_method")
    private String deliveryMethod;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "order_status")
    private String orderStatus;
    @Column(name = "delivery_status")
    private String deliveryStatus;
    @Column(name = "payment_status")
    private String paymentStatus;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private Customer customer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        return id !=null && id.equals(((Order) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
