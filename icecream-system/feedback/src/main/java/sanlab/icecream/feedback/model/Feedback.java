package sanlab.icecream.feedback.model;

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
@Entity(name = "Feedback")
@Table(name = "feedback")
public class Feedback extends AbstractAuditEntity {

    @Id
    @SequenceGenerator(name = "feedback_id_sequence", sequenceName = "feedback_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feedback_id_sequence")
    private Long id;

    private String content;
    private Float ratingStart;

    private Long customerId;
    private Long productId;
    private String productName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Feedback)) {
            return false;
        }
        return id != null && id.equals(((Feedback) o).id);
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
