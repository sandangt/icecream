package sanlab.icecream.fundamentum.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    protected UUID id;
    protected String contactName;
    protected String phone;
    protected String addressLine1;
    protected String addressLine2;
    protected String city;
    protected String zipCode;
    protected String district;
    protected String stateOrProvince;
    protected String country;
}
