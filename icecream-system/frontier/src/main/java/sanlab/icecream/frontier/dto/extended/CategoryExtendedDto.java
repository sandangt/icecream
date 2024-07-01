package sanlab.icecream.frontier.dto.extended;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.dto.core.CategoryDto;
import sanlab.icecream.frontier.dto.core.ImageDto;
import sanlab.icecream.frontier.dto.core.ProductDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryExtendedDto extends CategoryDto {
    List<ProductDto> products;
    private ImageDto avatar;
}
