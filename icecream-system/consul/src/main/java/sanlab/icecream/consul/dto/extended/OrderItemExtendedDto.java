package sanlab.icecream.consul.dto.extended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.consul.dto.core.OrderDto;
import sanlab.icecream.consul.dto.core.OrderItemDto;
import sanlab.icecream.consul.dto.core.ProductDto;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemExtendedDto extends OrderItemDto {
    private ProductDto product;
    private OrderDto order;
}
