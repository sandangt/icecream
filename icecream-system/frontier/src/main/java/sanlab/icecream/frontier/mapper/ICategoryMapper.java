package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.viewmodel.dto.CategoryDto;
import sanlab.icecream.frontier.viewmodel.response.CategoryResponse;

import java.util.List;

@Mapper(componentModel = "spring", uses = { IProductMapper.class })
public interface ICategoryMapper {

    @Named("entityToDto")
    CategoryDto entityToDto(Category category);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<CategoryDto> entityToDto(List<Category> categories);

    @Named("entityToResponse")
    CategoryResponse entityToResponse(Category category);

    @Named("entityToResponseIter")
    @IterableMapping(qualifiedByName = "entityToResponse")
    List<CategoryResponse> entityToResponse(List<Category> categories);

    @Named("dtoToEntity")
    Category dtoToEntity(CategoryDto categoryDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Category> dtoToEntity(List<CategoryDto> categoryDtos);

}
