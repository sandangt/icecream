package sanlab.icecream.inventory.model;

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


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Stock")
@Table(name = "stock")
public class Stock extends AbstractAuditEntity {
    @Id
    @SequenceGenerator(name = "stock_id_sequence", sequenceName = "stock_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_id_sequence")
    private Long id;

    private Long quantity;
    private Long reservedQuantity;

    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        return id != null && id.equals(((Stock) o).id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
