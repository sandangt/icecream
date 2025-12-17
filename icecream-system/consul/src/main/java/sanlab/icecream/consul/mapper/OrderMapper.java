package sanlab.icecream.consul.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import sanlab.icecream.consul.dto.core.OrderDto;
import sanlab.icecream.consul.dto.extended.OrderExtendedDto;
import sanlab.icecream.consul.model.Order;

import java.util.List;

@Mapper(componentModel = "spring", uses = { SharedMapper.class, OrderItemMapper.class, AddressMapper.class })
public interface OrderMapper {

    //region To DTO
    @Named("entityToDto")
    @Mapping(target = "deliveryMethod", source = "deliveryMethod", qualifiedByName = "nameToEDeliveryMethod")
    @Mapping(target = "paymentMethod", source = "paymentMethod", qualifiedByName = "nameToEPaymentMethod")
    @Mapping(target = "orderStatus", source = "orderStatus", qualifiedByName = "nameToEOrderStatus")
    @Mapping(target = "deliveryStatus", source = "deliveryStatus", qualifiedByName = "nameToEDeliveryStatus")
    @Mapping(target = "paymentStatus", source = "paymentStatus", qualifiedByName = "nameToEPaymentStatus")
    OrderDto entityToDto(Order order);

    @Named("entityToDtoIter")
    @IterableMapping(qualifiedByName = "entityToDto")
    List<OrderDto> entityToDto(List<Order> orders);

    @Named("entityToExtendedDto")
    @Mapping(target = "deliveryMethod", source = "deliveryMethod", qualifiedByName = "nameToEDeliveryMethod")
    @Mapping(target = "paymentMethod", source = "paymentMethod", qualifiedByName = "nameToEPaymentMethod")
    @Mapping(target = "orderStatus", source = "orderStatus", qualifiedByName = "nameToEOrderStatus")
    @Mapping(target = "deliveryStatus", source = "deliveryStatus", qualifiedByName = "nameToEDeliveryStatus")
    @Mapping(target = "paymentStatus", source = "paymentStatus", qualifiedByName = "nameToEPaymentStatus")
    OrderExtendedDto entityToExtendedDto(Order order);

    @Named("entityToExtendedDtoIter")
    @IterableMapping(qualifiedByName = "entityToExtendedDto")
    List<OrderExtendedDto> entityToExtendedDto(List<Order> orders);
    //endregion

    //region To Entity
    @Named("dtoToEntity")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "deliveryMethod", source = "deliveryMethod", qualifiedByName = "eDeliveryMethodToName")
    @Mapping(target = "paymentMethod", source = "paymentMethod", qualifiedByName = "ePaymentMethodToName")
    @Mapping(target = "orderStatus", source = "orderStatus", qualifiedByName = "eOrderStatusToName")
    @Mapping(target = "deliveryStatus", source = "deliveryStatus", qualifiedByName = "eDeliveryStatusToName")
    @Mapping(target = "paymentStatus", source = "paymentStatus", qualifiedByName = "ePaymentStatusToName")
    Order dtoToEntity(OrderDto orderDto);

    @Named("dtoToEntityIter")
    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<Order> dtoToEntity(List<OrderDto> orderDtos);
    //endregion

}
