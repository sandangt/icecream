package sanlab.icecream.fundamentum.dto.exntended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.fundamentum.dto.core.CustomerDto;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.core.ProductDto;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FeedbackExtendedDto extends FeedbackDto {
    private ProductDto product;
    private CustomerDto customer;
}
