package sanlab.icecream.memoir.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.memoir.model.AuditLog;

import java.util.UUID;

public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {}
