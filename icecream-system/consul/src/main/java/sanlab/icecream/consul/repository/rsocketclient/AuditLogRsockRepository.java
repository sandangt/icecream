package sanlab.icecream.consul.repository.rsocketclient;

import sanlab.icecream.fundamentum.dto.AuditLogDto;

import java.util.List;
import java.util.Optional;

public interface AuditLogRsockRepository {

    List<AuditLogDto> getAll();

    Optional<AuditLogDto> getById();

}
