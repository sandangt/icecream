package sanlab.icecream.consul.dto.extended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.fundamentum.dto.core.AddressDto;
import sanlab.icecream.consul.dto.core.OrderDto;
import sanlab.icecream.consul.dto.core.OrderItemDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderExtendedDto extends OrderDto {
    private List<OrderItemDto> orderItems;
    private AddressDto address;
}
