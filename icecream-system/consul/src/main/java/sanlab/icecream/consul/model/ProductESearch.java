package sanlab.icecream.consul.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import static sanlab.icecream.fundamentum.constant.TableName.ES_PRODUCT;

@Builder
@Data
@Document(indexName = ES_PRODUCT)
public class ProductESearch {

    @Id
    private String id;

    @Field(name = "name", type = FieldType.Text, analyzer = "standard")
    private String name;

    @Field(name = "slug", type = FieldType.Keyword)
    @JsonProperty("slug")
    private String slug;

    @Field(name = "sku", type = FieldType.Keyword)
    private String sku;

    @Field(name = "price", type = FieldType.Double)
    private Double price;

    @Field(name = "description", type = FieldType.Text, analyzer = "standard")
    private String description;

    @Field(name = "brief_description", type = FieldType.Text, analyzer = "standard")
    private String briefDescription;

    @Field(name = "meta_title", type = FieldType.Text)
    private String metaTitle;

    @Field(name = "meta_description", type = FieldType.Text)
    private String metaDescription;

    @Field(name = "meta_keyword", type = FieldType.Text)
    private String metaKeyword;

    @Field(name = "is_featured", type = FieldType.Boolean)
    private Boolean isFeatured;

    @Field(name = "status", type = FieldType.Integer)
    private Integer status;

    @Field(name = "created_at", type = FieldType.Long)
    private Long createdAt;

    @Field(name = "modified_at", type = FieldType.Long)
    private Long modifiedAt;

}
