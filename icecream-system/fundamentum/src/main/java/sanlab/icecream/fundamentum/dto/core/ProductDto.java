package sanlab.icecream.fundamentum.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import sanlab.icecream.fundamentum.constant.EProductStatus;

import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    protected UUID id;
    protected String name;
    protected String slug;
    protected String briefDescription;
    protected String description;
    protected EProductStatus status;
    protected Double price;
    protected String sku;
    protected Boolean isFeatured;
    protected Long stockQuantity;
    protected String metaTitle;
    protected String metaKeyword;
    protected String metaDescription;
    protected Long createdAt;
    protected Long modifiedAt;
}
