package sanlab.icecream.frontier.dto.extended;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.dto.core.CartDto;
import sanlab.icecream.frontier.dto.core.CartItemDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class CartExtendedDto extends CartDto {
    private List<CartItemDto> cartItems;
}
