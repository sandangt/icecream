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
import static sanlab.icecream.fundamentum.constant.TableName.FEEDBACK;

@Entity
@Table(name = FEEDBACK)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Feedback extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String content;
    private Integer star;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private Customer customer;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;
        return id !=null && id.equals(((Feedback) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
