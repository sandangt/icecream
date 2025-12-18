package sanlab.icecream.memoir.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.fundamentum.dto.core.AuditLogDto;
import sanlab.icecream.memoir.mapper.AuditLogMapper;
import sanlab.icecream.memoir.repository.crud.AuditLogRepository;

@Service
@RequiredArgsConstructor
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;
    private final AuditLogMapper auditLogMapper;

    public void save(AuditLogDto dto) {
        var auditLog = auditLogMapper.dtoToEntity(dto);
        auditLogRepository.save(auditLog);
    }

}
