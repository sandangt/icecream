package sanlab.icecream.gateway.viewmodel.customer;

import java.time.OffsetDateTime;

import lombok.Builder;


@Builder
public record CustomerVm(Long id,
                         String email,
                         String phone,
                         String lastName,
                         String firstName,
                         Boolean isActive,
                         String username,
                         String mediaId,
                         OffsetDateTime createdOn,
                         String createdBy,
                         OffsetDateTime lastModifiedOn,
                         String lastModifiedBy) {}
