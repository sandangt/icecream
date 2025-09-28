package sanlab.icecream.consul.dto.extended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.consul.dto.core.CartDto;
import sanlab.icecream.consul.dto.core.CartItemDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartExtendedDto extends CartDto {
    private List<CartItemExtendedDto> cartItems;
}
