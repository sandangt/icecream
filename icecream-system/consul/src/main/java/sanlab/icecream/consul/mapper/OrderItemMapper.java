package sanlab.icecream.consul.mapper;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.dto.core.OrderItemDto;
import sanlab.icecream.consul.dto.extended.OrderItemExtendedDto;
import sanlab.icecream.consul.model.OrderItem;

import java.util.List;

@Mapper(componentModel = "spring", uses = { ProductMapper.class, OrderMapper.class })
public interface OrderItemMapper {

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
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem dtoToEntity(OrderItemDto orderItemDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<OrderItem> dtoToEntity(List<OrderItemDto> orderItemDtos);
    //endregion

}
