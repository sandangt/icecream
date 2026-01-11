package sanlab.icecream.consul.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.model.ImageESearch;
import sanlab.icecream.fundamentum.dto.core.ImageDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class} )
public interface ImageMapper {

    //region To DTO
    @Named("entityToDto")
    @Mapping(target = "type", source = "type", qualifiedByName = "nameToEImageType")
    ImageDto entityToDto(Image image);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<ImageDto> entityToDto(List<Image> images);

    @Named("searchEntityToDto")
    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUuid")
    @Mapping(target = "type", source = "type", qualifiedByName = "nameToEImageType")
    ImageDto searchEntityToDto(ImageESearch image);

    @Named("searchEntityToDtoIter")
    @IterableMapping(qualifiedByName = "searchEntityToDto")
    List<ImageDto> searchEntityToDto(List<ImageESearch> images);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @Mapping(target = "type", source = "type", qualifiedByName = "eImageTypeToName")
    Image dtoToEntity(ImageDto imageDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Image> dtoToEntity(List<ImageDto> imageDtos);
    //endregion

}
