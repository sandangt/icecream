package sanlab.icecream.frontier.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Getter
    @Setter
    private Long createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    @Getter
    @Setter
    private Long modifiedAt;

}
