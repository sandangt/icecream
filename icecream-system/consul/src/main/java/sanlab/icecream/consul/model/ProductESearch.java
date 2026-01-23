package sanlab.icecream.consul.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

import static sanlab.icecream.fundamentum.constant.TableName.ES_PRODUCT;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = ES_PRODUCT, createIndex = false)
public class ProductESearch {

    @Id
    private String id;

    private String name;

    private String slug;

    private String sku;

    private Double price;

    private String description;

    private String briefDescription;

    private String metaTitle;

    private String metaDescription;

    private String metaKeyword;

    private Boolean isFeatured;

    private String status;

    private Long createdAt;

    private Long modifiedAt;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<CategoryESearch> categories;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<ImageESearch> media;

    private ImageESearch featuredBanner;

}
