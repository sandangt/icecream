package sanlab.icecream.sharedlib.abstractentity;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Configurable;

import org.springframework.data.auditing.AuditingHandler;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Configurable
class CustomAuditingEntityListener extends AuditingEntityListener {
    public CustomAuditingEntityListener(ObjectFactory<AuditingHandler> handler) {
        super.setAuditingHandler(handler);
    }

    @Override
    @PrePersist
    public void touchForCreate(Object target) {
        AbstractAuditEntity entity = (AbstractAuditEntity) target;
        if (entity.getCreatedBy() == null) {
            super.touchForCreate(target);
        }
    }

    @Override
    @PreUpdate
    public void touchForUpdate(Object target) {
        AbstractAuditEntity entity = (AbstractAuditEntity) target;
        if (entity.getLastModifiedBy() == null) {
            super.touchForUpdate(target);
        }
    }
}
