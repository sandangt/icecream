package sanlab.icecream.consul.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;
import static sanlab.icecream.fundamentum.constant.TableName.PRODUCT;

@Entity
@Table(name = PRODUCT)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractAuditEntity {

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

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "product_category",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Builder.Default
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "product_image",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    @Builder.Default
    private Set<Image> media = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = LAZY, orphanRemoval = true)
    @Builder.Default
    private List<Feedback> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = EAGER, orphanRemoval = true)
    @Builder.Default
    private List<Stock> stocks = new ArrayList<>();

    @OneToOne(fetch = EAGER)
    @JoinColumn(name = "featured_banner", referencedColumnName = "id")
    private Image featuredBanner;

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

}
