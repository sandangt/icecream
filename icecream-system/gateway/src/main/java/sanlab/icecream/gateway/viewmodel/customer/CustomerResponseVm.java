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
        this.id = customerVm.id();
        this.email = customerVm.email();
        this.phone = customerVm.phone();
        this.lastName = customerVm.lastName();
        this.firstName = customerVm.firstName();
        this.isActive = customerVm.isActive();
        this.username = customerVm.username();
        this.mediaId = customerVm.mediaId();
        this.createdOn = customerVm.createdOn();
        this.createdBy = customerVm.createdBy();
        this.lastModifiedOn = customerVm.lastModifiedOn();
        this.lastModifiedBy = customerVm.lastModifiedBy();
    }
}
