package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.consul.dto.extended.ProductExtendedDto;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.dto.core.ProductDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CategoryMapper.class, ImageMapper.class, StockMapper.class })
public interface ProductMapper extends BaseMapper {

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

    //region To Search DTO

    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @BeanMapping(ignoreByDefault = true)
    Product dtoToEntity(ProductDto productDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Product> dtoToEntity(List<ProductDto> productDtos);
    //endregion

}
