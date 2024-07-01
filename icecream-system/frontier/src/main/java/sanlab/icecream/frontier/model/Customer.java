package sanlab.icecream.frontier.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.constant.ECustomerStatus;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "customer")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Customer extends AbstractAuditEntity {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    private String email;
    private String username;
    private String phone;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private ECustomerStatus status;

    @ManyToMany(fetch = EAGER, cascade = MERGE)
    @JoinTable(name = "customer_image",
        joinColumns = @JoinColumn(name = "customer_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Image> media = new HashSet<>();

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
