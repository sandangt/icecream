package icecream.product.model;

import java.util.*;

import javax.persistence.*;

import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="categories")
public class Tag extends Base {

    @Id
    @Column(name="id", columnDefinition = "UUID")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column(name = "name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Product> products = new HashSet<>();

    public Tag(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equal(this.id, tag.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
