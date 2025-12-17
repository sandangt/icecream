package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.fundamentum.dto.core.ProductDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class, CategoryMapper.class, ImageMapper.class, StockMapper.class })
public interface ProductMapper {

    //region To DTO
    @Named("entityToDto")
    @Mapping(target = "status", source = "status", qualifiedByName = "nameToEProductStatus")
    ProductDto entityToDto(Product product);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<ProductDto> entityToDto(List<Product> products);

    @Named("entityToExtendedDto")
    @Mapping(target = "status", source = "status", qualifiedByName = "nameToEProductStatus")
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
    @Mapping(target = "status", source = "status", qualifiedByName = "eProductStatusToName")
    Product dtoToEntity(ProductDto productDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Product> dtoToEntity(List<ProductDto> productDtos);
    //endregion

}
