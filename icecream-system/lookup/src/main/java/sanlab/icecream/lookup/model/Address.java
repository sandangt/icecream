package sanlab.icecream.lookup.model;

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
@Entity(name = "Address")
@Table(name = "address")
public class Address extends AbstractAuditEntity {
    @Id
    @SequenceGenerator(name = "address_id_sequence", sequenceName = "address_id_sequence", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_sequence")
    private Long id;
    private String address;
    private String city;
    private String country;
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        return id != null && id.equals(((Address) o).id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
