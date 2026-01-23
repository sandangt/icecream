package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findFirstByOrderByName();

    List<Category> findByIdIn(List<UUID> id);

    Set<Category> findAllByProducts_Id(UUID productId);

    boolean existsById(UUID id);

    @Query("SELECT product FROM Product product JOIN product.categories category WHERE category.id = :id")
    List<Product> findAllProductsById(@Param("id") UUID id);

}
