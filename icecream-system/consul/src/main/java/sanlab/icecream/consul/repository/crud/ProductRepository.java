package sanlab.icecream.consul.repository.crud;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    @NotNull
    Page<Product> findAll(@NotNull Pageable pageable);
    Page<Product> findAllByIsFeaturedTrue(@NotNull Pageable pageable);
    long countByIsFeaturedTrue();
    Optional<Product> findFirstByOrderByName();
    Optional<Product> findFirstBySlug(String slug);
    List<Product> findAllByIdIn(List<UUID> ids);
    boolean existsById(UUID id);
    @Query("SELECT img FROM Product p JOIN p.media img WHERE p.id = :id")
    Set<Image> findAllMediaById(@Param("id") UUID id);
    @Query("SELECT img FROM Product p JOIN p.featuredBanner img WHERE p.id = :id ORDER BY img.id ASC")
    Optional<Image> findFirstFeaturedBannerById(@Param("id") UUID id);

}
