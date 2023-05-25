package sanlab.icecream.sharedlib.abstractentity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@SuperBuilder
@RequiredArgsConstructor
@MappedSuperclass
@Getter
@Setter
@EntityListeners(CustomAuditingEntityListener.class)
public abstract class AbstractAuditEntity implements Serializable {
    @CreationTimestamp
    private OffsetDateTime createdOn;
    @CreatedBy
    private String createdBy;
    @UpdateTimestamp
    private OffsetDateTime lastModifiedOn;
    @LastModifiedBy
    private String lastModifiedBy;
}
