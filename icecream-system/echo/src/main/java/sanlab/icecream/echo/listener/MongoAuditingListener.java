package sanlab.icecream.echo.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import sanlab.icecream.echo.model.Auditable;

@Component
public class MongoAuditingListener extends AbstractMongoEventListener<Object> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object obj = event.getSource();
        if (obj instanceof Auditable) {
            Auditable auditable = (Auditable) obj;
            if (auditable.getCreatedAt() == null) {
                auditable.setCreatedAt(System.currentTimeMillis());
            }
        }
    }
}
