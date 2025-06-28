package sanlab.icecream.consul.model;

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
import static sanlab.icecream.fundamentum.constant.TableName.ORDER_ITEM;

@Entity
@Table(name = ORDER_ITEM)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    private Long quantity;
    private String note;
    private Double price;
    private Double discount;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem)) return false;
        return id !=null && id.equals(((OrderItem) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
