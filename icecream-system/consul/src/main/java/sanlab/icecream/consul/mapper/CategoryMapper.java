package sanlab.icecream.consul.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.model.CategoryESearch;
import sanlab.icecream.fundamentum.dto.exntended.CategoryExtendedDto;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.fundamentum.dto.core.CategoryDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class, ImageMapper.class })
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

    @Named("searchEntityToDto")
    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUuid")
    CategoryDto searchEntityToDto(CategoryESearch category);

    @Named("searchEntityToDtoIter")
    @IterableMapping(qualifiedByName = "searchEntityToDto")
    List<CategoryDto> searchEntityToDto(List<CategoryESearch> categories);
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
