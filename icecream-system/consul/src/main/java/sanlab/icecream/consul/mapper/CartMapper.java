package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.dto.core.CartDto;
import sanlab.icecream.consul.dto.extended.CartExtendedDto;
import sanlab.icecream.consul.model.Cart;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CartItemMapper.class }, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapper {

    //region To DTO
    @Named("entityToDto")
    CartDto entityToDto(Cart cart);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<CartDto> entityToDto(List<Cart> carts);

    @Named("entityToExtendedDto")
    @Mapping(target = "cartItems", source = "cartItems", qualifiedByName = "entityToExtendedDtoIter")
    CartExtendedDto entityToExtendedDto(Cart cart);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<CartExtendedDto> entityToExtendedDto(List<Cart> products);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Cart dtoToEntity(CartDto cartDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Cart> dtoToEntity(List<CartDto> cartDtos);
    //endregion

}
