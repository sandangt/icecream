package sanlab.icecream.lookup.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Entity(name = "Media")
@Table(name = "media")
public class Media extends AbstractAuditEntity {
    @Id
    @SequenceGenerator(name = "media_id_sequence", sequenceName = "media_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_id_sequence")
    private Long id;

    private String caption;
    private String filePath;
    @Lob
    private Byte[] data;
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
