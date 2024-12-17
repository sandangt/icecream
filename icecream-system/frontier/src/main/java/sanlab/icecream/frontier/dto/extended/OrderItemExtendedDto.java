package sanlab.icecream.frontier.dto.extended;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.dto.core.OrderDto;
import sanlab.icecream.frontier.dto.core.OrderItemDto;
import sanlab.icecream.frontier.dto.core.ProductDto;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderItemExtendedDto extends OrderItemDto {
    private ProductDto product;
    private OrderDto order;
}
