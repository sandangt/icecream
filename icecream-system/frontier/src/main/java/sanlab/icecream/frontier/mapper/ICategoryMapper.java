package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.extended.CategoryExtendedDto;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.dto.core.CategoryDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { IImageMapper.class })
public interface ICategoryMapper {

    //region To DTO
    @Named("entityToDto")
    CategoryDto entityToDto(Category category);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<CategoryDto> entityToDto(List<Category> categories);

    @Named("entityToExtendedDto")
    CategoryExtendedDto entityToExtendedDto(Category category);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<CategoryExtendedDto> entityToExtendedDto(List<Category> categories);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Category dtoToEntity(CategoryDto categoryDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Category> dtoToEntity(List<CategoryDto> categoryDtos);
    //endregion

}
