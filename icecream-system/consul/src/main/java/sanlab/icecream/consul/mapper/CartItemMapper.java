package sanlab.icecream.consul.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.dto.core.CartItemDto;
import sanlab.icecream.consul.dto.extended.CartItemExtendedDto;
import sanlab.icecream.consul.model.CartItem;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CartMapper.class, ProductMapper.class })
public interface CartItemMapper {

    //region To DTO
    @Named("entityToDto")
    CartItemDto entityToDto(CartItem cartItem);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<CartItemDto> entityToDto(List<CartItem> cartItems);

    @Named("entityToExtendedDto")
    CartItemExtendedDto entityToExtendedDto(CartItem cartItem);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<CartItemExtendedDto> entityToExtendedDto(List<CartItem> cartItems);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    CartItem dtoToEntity(CartItemDto cartItemDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<CartItem> dtoToEntity(List<CartItemDto> cartItemDtos);
    //endregion

}
