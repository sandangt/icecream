package sanlab.icecream.gateway.viewmodel.lookup;

import java.time.OffsetDateTime;


public record MediaVm(
    String id,
    String caption,
    String filepath,
    String mediaType,
    OffsetDateTime createdOn,
    String createdBy,
    OffsetDateTime lastModifiedOn,
    String lastModifiedBy
) {}
