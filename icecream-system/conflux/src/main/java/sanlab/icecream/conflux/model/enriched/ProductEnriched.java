package sanlab.icecream.conflux.model.enriched;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEnriched {

    private String id;
    private String name;
    private String slug;
    private String briefDescription;
    private String description;
    private String status;
    private Double price;
    private String sku;
    private Boolean isFeatured;
    private Long stockQuantity;
    private String metaTitle;
    private String metaKeyword;
    private String metaDescription;
    private Long createdAt;
    private Long modifiedAt;

    private List<CategoryEnriched> categories;
    private List<ImageEnriched> media;
    private ImageEnriched featuredBanner;

}
