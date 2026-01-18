package sanlab.icecream.memoir.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.fundamentum.dto.AuditLogDto;
import sanlab.icecream.memoir.model.AuditLog;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SharedMapper.class})
public interface AuditLogMapper {

    @Named("entityToDto")
    AuditLogDto entityToDto(AuditLog entity);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<AuditLogDto> entityToDto(List<AuditLog> entities);

    @Named("dtoToEntity")
    AuditLog dtoToEntity(AuditLogDto dto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<AuditLog> dtoToEntity(List<AuditLogDto> dto);

}
