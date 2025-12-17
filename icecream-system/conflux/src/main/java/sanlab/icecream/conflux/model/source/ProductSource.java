package sanlab.icecream.conflux.model.source;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSource {

    private String id;
    private String name;
    private String slug;
    @JsonProperty("brief_description")
    private String briefDescription;
    private String description;
    private String status;
    private Double price;
    private String sku;
    @JsonProperty("is_featured")
    private Boolean isFeatured;
    @JsonProperty("stock_quantity")
    private Long stockQuantity;
    @JsonProperty("meta_title")
    private String metaTitle;
    @JsonProperty("featured_banner")
    private String featuredBannerId;
    @JsonProperty("meta_keyword")
    private String metaKeyword;
    @JsonProperty("meta_description")
    private String metaDescription;
    @JsonProperty("created_at")
    private Long createdAt;
    @JsonProperty("modified_at")
    private Long modifiedAt;

    @JsonProperty("__op")
    private String operation;
    @JsonProperty("__ts_ms")
    private Long tsMs;
    @JsonProperty("__deleted")
    private String deleted;

}
