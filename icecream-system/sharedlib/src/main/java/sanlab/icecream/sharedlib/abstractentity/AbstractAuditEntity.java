package sanlab.icecream.sharedlib.abstractentity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

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
