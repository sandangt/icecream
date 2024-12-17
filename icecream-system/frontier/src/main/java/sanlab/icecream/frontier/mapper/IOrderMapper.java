package sanlab.icecream.frontier.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.frontier.dto.core.OrderDto;
import sanlab.icecream.frontier.dto.extended.OrderExtendedDto;
import sanlab.icecream.frontier.model.Order;

import java.util.List;

@Mapper(componentModel = "spring", uses = { IOrderItemMapper.class, IAddressMapper.class })
public interface IOrderMapper {

    //region To DTO
    @Named("entityToDto")
    OrderDto entityToDto(Order order);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<OrderDto> entityToDto(List<Order> orders);

    @Named("entityToExtendedDto")
    OrderExtendedDto entityToExtendedDto(Order order);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<OrderExtendedDto> entityToExtendedDto(List<Order> orders);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    Order dtoToEntity(OrderDto orderDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Order> dtoToEntity(List<OrderDto> orderDtos);
    //endregion

}
