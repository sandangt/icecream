package sanlab.icecream.gateway.viewmodel.product;

import java.time.OffsetDateTime;

import lombok.Builder;


@Builder
public record ProductVm(Long id,
                        String name,
                        String briefDescription,
                        String description,
                        String specification,
                        String sku,
                        String slug,
                        Double price,
                        Long stockQuantity,
                        String metaTitle,
                        String metaKeyword,
                        String metaDescription,
                        String mediaId,
                        OffsetDateTime createdOn,
                        String createdBy,
                        OffsetDateTime lastModifiedOn,
                        String lastModifiedBy) {}
