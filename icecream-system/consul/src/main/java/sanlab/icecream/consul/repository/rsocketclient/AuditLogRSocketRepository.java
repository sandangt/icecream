package sanlab.icecream.consul.repository.rsocketclient;

import sanlab.icecream.fundamentum.dto.core.AuditLogDto;

import java.util.List;
import java.util.Optional;

public interface AuditLogRSocketRepository {

    List<AuditLogDto> getAll();

    Optional<AuditLogDto> getById();

}
