package sanlab.icecream.lookup.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import sanlab.icecream.sharedlib.abstractentity.AbstractAuditEntity;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Media")
@Table(name = "media")
public class Media extends AbstractAuditEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String caption;
    private String filepath;
    private String mediaType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Media)) {
            return false;
        }
        return id != null && id.equals(((Media) o).id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
