package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import sanlab.icecream.consul.dto.core.OrderDto;
import sanlab.icecream.consul.dto.extended.OrderExtendedDto;
import sanlab.icecream.consul.model.Order;

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
    @BeanMapping(ignoreByDefault = true)
    Order dtoToEntity(OrderDto orderDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Order> dtoToEntity(List<OrderDto> orderDtos);
    //endregion

}
