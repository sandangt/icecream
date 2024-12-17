package sanlab.icecream.frontier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Table(name = "address")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Address extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "contact_name")
    private String contactName;
    private String phone;
    @Column(name = "address_line_1")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    private String city;
    @Column(name = "zip_code")
    private String zipCode;
    private String district;
    @Column(name = "state_or_province")
    private String stateOrProvince;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        return id !=null && id.equals(((Address) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
