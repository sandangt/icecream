package sanlab.icecream.gateway.viewmodel.customer;

import java.time.OffsetDateTime;

import lombok.Data;


@Data
public class CustomerResponseVm {
    private final Long id;
    private final String email;
    private final String phone;
    private final String lastName;
    private final String firstName;
    private final Boolean isActive;
    private final String username;
    private final String mediaId;
    private final OffsetDateTime createdOn;
    private final String createdBy;
    private final OffsetDateTime lastModifiedOn;
    private final String lastModifiedBy;

    public CustomerResponseVm(CustomerVm customerVm) {
        this.id = customerVm.getId();
        this.email = customerVm.getEmail();
        this.phone = customerVm.getPhone();
        this.lastName = customerVm.getLastName();
        this.firstName = customerVm.getFirstName();
        this.isActive = customerVm.getIsActive();
        this.username = customerVm.getUsername();
        this.mediaId = customerVm.getMediaId();
        this.createdOn = customerVm.getCreatedOn();
        this.createdBy = customerVm.getCreatedBy();
        this.lastModifiedOn = customerVm.getLastModifiedOn();
        this.lastModifiedBy = customerVm.getLastModifiedBy();
    }
}
