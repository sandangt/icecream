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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Address")
@Table(name = "address")
public class Address {
    @Id
    @SequenceGenerator(name = "media_id_sequence", sequenceName = "media_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_id_sequence")
    private Long id;
    private String phone;
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
