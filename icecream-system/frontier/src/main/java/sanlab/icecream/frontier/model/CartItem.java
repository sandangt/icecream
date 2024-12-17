package sanlab.icecream.frontier.model;

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

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "cart_item")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class CartItem extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long quantity;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        return id !=null && id.equals(((CartItem) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
