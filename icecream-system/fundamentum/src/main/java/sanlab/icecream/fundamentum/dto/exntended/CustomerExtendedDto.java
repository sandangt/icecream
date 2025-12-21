package sanlab.icecream.fundamentum.dto.exntended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.fundamentum.dto.core.AddressDto;
import sanlab.icecream.fundamentum.dto.core.CustomerDto;
import sanlab.icecream.fundamentum.dto.core.ImageDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerExtendedDto extends CustomerDto {
    private AddressDto primaryAddress;
    private List<ImageDto> media;
    private List<AddressDto> addresses;
}
