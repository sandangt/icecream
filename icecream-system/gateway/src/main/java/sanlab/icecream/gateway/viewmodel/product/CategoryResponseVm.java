package sanlab.icecream.gateway.viewmodel.product;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;
import sanlab.icecream.gateway.viewmodel.lookup.MediaVm;


@Data
public class CategoryResponseVm {
    private final Long id;
    private final String name;
    private final String description;
    private final String slug;
    private final String metaKeyword;
    private final String metaDescription;
    private final OffsetDateTime createdOn;
    private final String createdBy;
    private final OffsetDateTime lastModifiedOn;
    private final String lastModifiedBy;
    private final List<ProductVm> productList;
    private final MediaVm media;
    public CategoryResponseVm(CategoryVm categoryVm, List<ProductVm> productList, MediaVm mediaVm) {
        this.id = categoryVm.id();
        this.name = categoryVm.name();
        this.description = categoryVm.description();
        this.slug = categoryVm.slug();
        this.metaKeyword = categoryVm.metaKeyword();
        this.metaDescription = categoryVm.metaDescription();
        this.createdOn = categoryVm.createdOn();
        this.createdBy = categoryVm.createdBy();
        this.lastModifiedOn = categoryVm.lastModifiedOn();
        this.lastModifiedBy = categoryVm.lastModifiedBy();
        this.productList = productList;
        this.media = mediaVm;
    }
}
