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
@Document(indexName = ES_PRODUCT)
public class ProductESearch {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String name;

    @Field(type = FieldType.Keyword)
    private String slug;

    @Field(type = FieldType.Keyword)
    private String sku;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String briefDescription;

    @Field(type = FieldType.Text)
    private String metaTitle;

    @Field(type = FieldType.Text)
    private String metaDescription;

    @Field(type = FieldType.Text)
    private String metaKeyword;

    @Field(type = FieldType.Boolean)
    private Boolean isFeatured;

    @Field(type = FieldType.Keyword)
    private String status;

    @Field(type = FieldType.Long)
    private Long createdAt;

    @Field( type = FieldType.Long)
    private Long modifiedAt;

    private List<CategoryESearch> categories;
    private List<ImageESearch> media;
    private ImageESearch featuredBanner;

}
