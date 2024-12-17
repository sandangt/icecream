package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.core.CartDto;
import sanlab.icecream.frontier.dto.extended.CartExtendedDto;
import sanlab.icecream.frontier.model.Cart;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ICartItemMapper.class })
public interface ICartMapper {

    //region To DTO
    @Named("entityToDto")
    CartDto entityToDto(Cart cart);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<CartDto> entityToDto(List<Cart> carts);

    @Named("entityToExtendedDto")
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
