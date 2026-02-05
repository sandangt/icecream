package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.model.ProductESearch;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.fundamentum.dto.core.ProductDto;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class, CategoryMapper.class, ImageMapper.class, StockMapper.class, AddressMapper.class })
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
    @Mapping(target = "stocks", source = "stocks", qualifiedByName = "entityToExtendedDtoIter")
    ProductExtendedDto entityToExtendedDto(Product product);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<ProductExtendedDto> entityToExtendedDto(List<Product> products);

    @Named("searchEntityToDto")
    @Mapping(target = "id", source = "id", qualifiedByName = "stringToUuid")
    @Mapping(target = "status", source = "status", qualifiedByName = "nameToEProductStatus")
    @Mapping(target = "stockQuantity", ignore = true)
    @Mapping(target = "stocks", ignore = true)
    ProductExtendedDto searchEntityToDto(ProductESearch product);

    @Named("searchEntityToDtoIter")
    @IterableMapping(qualifiedByName = "searchEntityToDto")
    List<ProductExtendedDto> searchEntityToDto(List<ProductESearch> product);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @Mapping(target = "status", source = "status", qualifiedByName = "eProductStatusToName")
    Product dtoToEntity(ProductDto productDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Product> dtoToEntity(List<ProductDto> productDtos);
    //endregion

}
