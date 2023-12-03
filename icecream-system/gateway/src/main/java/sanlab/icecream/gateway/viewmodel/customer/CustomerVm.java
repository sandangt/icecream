package sanlab.icecream.gateway.viewmodel.customer;

import java.time.OffsetDateTime;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class CustomerVm {
    private Long id;
    private String email;
    private String phone;
    private String lastName;
    private String firstName;
    private Boolean isActive;
    private String username;
    private String mediaId;
    private OffsetDateTime createdOn;
    private String createdBy;
    private OffsetDateTime lastModifiedOn;
    private String lastModifiedBy;
}
