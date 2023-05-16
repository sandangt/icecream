package sanlab.icecream.payment.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sanlab.icecream.sharedlib.abstractentity.AbstractAuditEntity;
import sanlab.icecream.sharedlib.enumeration.EPaymentMethod;
import sanlab.icecream.sharedlib.enumeration.EPaymentStatus;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Payment")
@Table(name = "payment")
public class Payment extends AbstractAuditEntity {
    @Id
    @SequenceGenerator(name = "payment_id_sequence", sequenceName = "payment_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_sequence")
    private Long id;

    private BigDecimal amount;
    private BigDecimal paymentFee;
    private EPaymentMethod paymentMethod;
    private EPaymentStatus paymentStatus;

    private Long orderId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }
    @Override
    public int hashCode() {
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }
}
