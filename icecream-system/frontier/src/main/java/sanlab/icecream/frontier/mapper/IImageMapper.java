package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.model.Image;
import sanlab.icecream.frontier.dto.core.ImageDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IImageMapper {

    //region To DTO
    @Named("entityToDto")
    ImageDto entityToDto(Image image);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<ImageDto> entityToDto(List<Image> images);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Image dtoToEntity(ImageDto imageDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Image> dtoToEntity(List<ImageDto> imageDtos);
    //endregion

}
