package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.frontier.model.Product;
import sanlab.icecream.frontier.viewmodel.dto.ProductDto;
import sanlab.icecream.frontier.viewmodel.response.ProductResponse;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ICategoryMapper.class, IImageMapper.class })
public interface IProductMapper {

    @Named("entityToDto")
    ProductDto entityToDto(Product product);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<ProductDto> entityToDto(List<Product> products);

    @Named("entityToResponse")
    ProductResponse entityToResponse(Product product);

    @Named("entityToResponseIter")
    @IterableMapping(qualifiedByName = "entityToResponse")
    List<ProductResponse> entityToResponse(List<Product> products);

    @Named("dtoToEntity")
    Product dtoToEntity(ProductDto productDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Product> dtoToEntity(List<ProductDto> productDtos);

    @Named("dtoToResponse")
    @Mapping(target = "categories", ignore = true)
    ProductResponse dtoToResponse(ProductDto productDto);

    @Named("dtoToResponseIter")
    @IterableMapping(qualifiedByName = "dtoToResponse")
    List<ProductResponse> dtoToResponse(List<ProductDto> productDtos);

}
