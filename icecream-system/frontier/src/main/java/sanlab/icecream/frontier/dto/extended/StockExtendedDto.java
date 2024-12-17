package sanlab.icecream.frontier.dto.extended;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.frontier.dto.core.AddressDto;
import sanlab.icecream.frontier.dto.core.StockDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class StockExtendedDto extends StockDto {
    private List<AddressDto> addresses;
}
