package sanlab.icecream.horus.model;

import static sanlab.icecream.horus.constant.TableName.CONSUL_PRODUCT;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = CONSUL_PRODUCT)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String slug;
    @Column(name = "brief_description")
    private String briefDescription;
    private String description;
    private String status;
    private Double price;
    private String sku;
    @Column(name = "is_featured")
    private Boolean isFeatured;
    @Column(name = "stock_quantity")
    private Long stockQuantity;
    @Column(name = "meta_title")
    private String metaTitle;
    @Column(name = "meta_keyword")
    private String metaKeyword;
    @Column(name = "meta_description")
    private String metaDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return id !=null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    private Long modifiedAt;

}

