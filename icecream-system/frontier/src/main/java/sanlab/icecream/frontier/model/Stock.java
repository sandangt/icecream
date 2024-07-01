package sanlab.icecream.frontier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "stock")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Stock extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long quantity;
    @Column(name = "reserved_quantity")
    private Long reservedQuantity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToMany(fetch = EAGER, cascade = MERGE)
    @JoinTable(name = "stock_address",
        joinColumns = @JoinColumn(name = "stock_id"),
        inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        return id !=null && id.equals(((Stock) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
