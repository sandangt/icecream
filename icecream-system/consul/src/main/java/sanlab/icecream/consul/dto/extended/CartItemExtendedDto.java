package sanlab.icecream.consul.dto.extended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.consul.dto.core.CartItemDto;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartItemExtendedDto extends CartItemDto {
    private ProductExtendedDto product;
}
