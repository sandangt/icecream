package sanlab.icecream.frontier.dto.extended;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.dto.core.AddressDto;
import sanlab.icecream.frontier.dto.core.OrderDto;
import sanlab.icecream.frontier.dto.core.OrderItemDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderExtendedDto extends OrderDto {
    private List<OrderItemDto> orderItems;
    private AddressDto address;
}
