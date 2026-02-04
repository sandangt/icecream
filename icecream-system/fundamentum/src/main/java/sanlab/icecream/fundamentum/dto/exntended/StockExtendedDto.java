package sanlab.icecream.fundamentum.dto.exntended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.fundamentum.dto.core.AddressDto;
import sanlab.icecream.fundamentum.dto.core.StockDto;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class StockExtendedDto extends StockDto {
    private AddressDto address;
}
