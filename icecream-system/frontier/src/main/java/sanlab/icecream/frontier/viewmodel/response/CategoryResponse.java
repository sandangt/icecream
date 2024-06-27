package sanlab.icecream.frontier.viewmodel.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.viewmodel.dto.CategoryDto;
import sanlab.icecream.frontier.viewmodel.dto.ImageDto;
import sanlab.icecream.frontier.viewmodel.dto.ProductDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryResponse extends CategoryDto {
//    private List<ProductDto> products;
    private ImageDto avatar;
}
