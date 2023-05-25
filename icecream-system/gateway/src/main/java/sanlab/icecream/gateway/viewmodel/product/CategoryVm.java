package sanlab.icecream.gateway.viewmodel.product;

import java.time.OffsetDateTime;

import lombok.Builder;


@Builder
public record CategoryVm(Long id,
                         String name,
                         String description,
                         String slug,
                         String metaKeyword,
                         String metaDescription,
                         String mediaId,
                         OffsetDateTime createdOn,
                         String createdBy,
                         OffsetDateTime lastModifiedOn,
                         String lastModifiedBy) {}
