package sanlab.icecream.consul.dto.extended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.consul.dto.core.CategoryDto;
import sanlab.icecream.consul.dto.core.ImageDto;
import sanlab.icecream.consul.dto.core.ProductDto;
import sanlab.icecream.consul.dto.core.StockDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductExtendedDto extends ProductDto {
    private List<CategoryDto> categories;
    private List<ImageDto> media;
    private List<StockDto> stocks;
    private ImageDto featuredBanner;
}
