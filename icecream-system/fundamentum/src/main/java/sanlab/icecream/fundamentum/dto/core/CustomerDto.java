package sanlab.icecream.fundamentum.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.fundamentum.constant.ECustomerStatus;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    protected UUID userId;
    protected String email;
    protected String username;
    protected String phone;
    protected String firstName;
    protected String lastName;
    protected ECustomerStatus status;
}
