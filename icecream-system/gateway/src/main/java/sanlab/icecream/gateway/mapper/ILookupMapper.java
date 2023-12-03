package sanlab.icecream.gateway.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sanlab.icecream.gateway.viewmodel.lookup.MediaVm;
import sanlab.icecream.sharedlib.proto.MediaDTO;


@Mapper(componentModel = "spring")
public interface ILookupMapper extends IBaseMapper {
    ILookupMapper INSTANCE = Mappers.getMapper(ILookupMapper.class);

    @Mapping(target = "createdOn", source = "createdOn", qualifiedByName = "timestampToOdt")
    @Mapping(target = "lastModifiedOn", source = "lastModifiedOn", qualifiedByName = "timestampToOdt")
    MediaVm dtoToVm(MediaDTO media);
}
