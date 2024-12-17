package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.core.OrderItemDto;
import sanlab.icecream.frontier.dto.extended.OrderItemExtendedDto;
import sanlab.icecream.frontier.model.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring", uses = { IProductMapper.class, IOrderMapper.class })
public interface IOrderItemMapper {

    //region To DTO
    @Named("entityToDto")
    OrderItemDto entityToDto(OrderItem orderItem);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<OrderItemDto> entityToDto(List<OrderItem> orderItems);

    @Named("entityToExtendedDto")
    OrderItemExtendedDto entityToExtendedDto(OrderItem orderItem);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<OrderItemExtendedDto> entityToExtendedDto(List<OrderItem> orderItems);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    OrderItem dtoToEntity(OrderItemDto orderItemDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<OrderItem> dtoToEntity(List<OrderItemDto> orderItemDtos);
    //endregion

}
