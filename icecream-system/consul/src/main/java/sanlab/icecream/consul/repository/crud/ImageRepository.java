package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {

    List<Image> findAllByOrderById();

    Optional<Image> findFirstByRelativePath(String relativePath);

    boolean existsById(UUID id);

    @Query("SELECT product FROM Product product JOIN product.media media WHERE media.id = :id")
    List<Product> findAllProductsById(UUID id);

}
