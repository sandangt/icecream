package sanlab.icecream.frontier.model;

import com.github.slugify.Slugify;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "category")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Category extends AbstractAuditEntity {

    private static final Slugify SLUG_BUILDER = Slugify.builder().build();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String slug;
    private String name;
    private String description;

    public Category(String name) {
        setName(name);
    }

    public Category(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public Category(String name, Image avatar) {
        setName(name);
        setAvatar(avatar);
    }

    public Category(String name, String description, Image avatar) {
        setName(name);
        setDescription(description);
        setAvatar(avatar);
    }

    public void setName(String name) {
        this.name = name;
        this.slug = SLUG_BUILDER.slugify(name);
    }

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "avatar_id", referencedColumnName = "id")
    private Image avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        return id !=null && id.equals(((Category) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
