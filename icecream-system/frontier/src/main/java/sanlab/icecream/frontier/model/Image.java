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
import sanlab.icecream.frontier.constant.EImageType;

import java.util.UUID;

@Entity
@Table(name = "image")
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Image extends AbstractAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    @Column(name = "relative_path", nullable = false)
    private String relativePath;
    private EImageType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;
        return id !=null && id.equals(((Image) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
