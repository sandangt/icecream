package sanlab.icecream.consul.dto.extended;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.consul.dto.core.AddressDto;
import sanlab.icecream.consul.dto.core.CustomerDto;
import sanlab.icecream.consul.dto.core.ImageDto;

import java.util.List;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerExtendedDto extends CustomerDto {
    private AddressDto primaryAddress;
    private List<ImageDto> media;
    private List<AddressDto> addresses;
}
