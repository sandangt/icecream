package sanlab.icecream.consul.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.dto.extended.CategoryExtendedDto;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.dto.core.CategoryDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ImageMapper.class })
public interface CategoryMapper {

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
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    Category dtoToEntity(CategoryDto categoryDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Category> dtoToEntity(List<CategoryDto> categoryDtos);
    //endregion

}
