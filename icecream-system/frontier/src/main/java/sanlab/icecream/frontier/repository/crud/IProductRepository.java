package sanlab.icecream.frontier.repository.crud;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Product;

import java.util.Optional;
import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID> {

    @NotNull
    Page<Product> findAll(@NotNull Pageable pageable);

    Optional<Product> findFirstByOrderByName();

}
