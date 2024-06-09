package sanlab.icecream.frontier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.constant.ECustomerStatus;

import java.util.UUID;

@Entity
@Table(name = "customer")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Customer extends AbstractAuditEntity {

    @Id
    private UUID userId;

    private String username;
    private String email;
    private String phone;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private ECustomerStatus isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        return userId !=null && userId.equals(((Customer) o).userId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
