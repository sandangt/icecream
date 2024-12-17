package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.extended.ProductExtendedDto;
import sanlab.icecream.frontier.model.Product;
import sanlab.icecream.frontier.dto.core.ProductDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ICategoryMapper.class, IImageMapper.class, IStockMapper.class })
public interface IProductMapper extends IBaseMapper {

    //region To DTO
    @Named("entityToDto")
    ProductDto entityToDto(Product product);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<ProductDto> entityToDto(List<Product> products);

    @Named("entityToExtendedDto")
    ProductExtendedDto entityToExtendedDto(Product product);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<ProductExtendedDto> entityToExtendedDto(List<Product> products);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Product dtoToEntity(ProductDto productDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Product> dtoToEntity(List<ProductDto> productDtos);
    //endregion

}
