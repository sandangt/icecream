package sanlab.icecream.gateway.viewmodel.product;

import java.time.OffsetDateTime;

import lombok.Data;


@Data
public class ProductResponseVm {
    private final Long id;
    private final String name;
    private final String briefDescription;
    private final String description;
    private final String specification;
    private final String sku;
    private final String slug;
    private final Double price;
    private final Long stockQuantity;
    private final String metaTitle;
    private final String metaKeyword;
    private final String metaDescription;
    private final String mediaId;
    private final OffsetDateTime createdOn;
    private final String createdBy;
    private final OffsetDateTime lastModifiedOn;
    private final String lastModifiedBy;
    private final CategoryVm category;

    public ProductResponseVm(ProductVm productVm, CategoryVm categoryVm) {
        this.id = productVm.id();
        this.name = productVm.name();
        this.briefDescription = productVm.briefDescription();
        this.description = productVm.description();
        this.specification = productVm.specification();
        this.sku = productVm.sku();
        this.slug = productVm.slug();
        this.price = productVm.price();
        this.stockQuantity = productVm.stockQuantity();
        this.metaTitle = productVm.metaTitle();
        this.metaKeyword = productVm.metaKeyword();
        this.metaDescription = productVm.metaDescription();
        this.mediaId = productVm.mediaId();
        this.createdOn = productVm.createdOn();
        this.createdBy = productVm.createdBy();
        this.lastModifiedOn = productVm.lastModifiedOn();
        this.lastModifiedBy = productVm.lastModifiedBy();
        this.category = categoryVm;
    }
}
