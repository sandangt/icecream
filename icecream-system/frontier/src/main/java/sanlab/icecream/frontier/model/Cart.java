package sanlab.icecream.frontier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "cart")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Cart extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "cart", fetch = EAGER, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private Customer customer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        return id !=null && id.equals(((Cart) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
