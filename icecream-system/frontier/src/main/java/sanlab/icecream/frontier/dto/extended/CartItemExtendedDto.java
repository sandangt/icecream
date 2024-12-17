package sanlab.icecream.frontier.dto.extended;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.dto.core.CartDto;
import sanlab.icecream.frontier.dto.core.CartItemDto;
import sanlab.icecream.frontier.dto.core.ProductDto;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class CartItemExtendedDto extends CartItemDto {
    private ProductDto product;
    private CartDto cart;
}
