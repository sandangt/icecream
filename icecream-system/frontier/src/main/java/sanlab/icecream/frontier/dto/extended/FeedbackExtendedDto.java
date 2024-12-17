package sanlab.icecream.frontier.dto.extended;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.dto.core.CustomerDto;
import sanlab.icecream.frontier.dto.core.FeedbackDto;
import sanlab.icecream.frontier.dto.core.ProductDto;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class FeedbackExtendedDto extends FeedbackDto {
    private ProductDto product;
    private CustomerDto customer;
}
