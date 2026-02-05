package sanlab.icecream.consul.repository.crud;

import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {

    List<Stock> findByIdIn(List<UUID> ids);

    List<Stock> findAllByProductId(UUID productId);

    Optional<Stock> findFirstByOrderByCreatedAt();

    List<Stock> findAllByOrderByCreatedAt();

    @NonNull
    Page<Stock> findAll(@NonNull Pageable pageable);

    @Query(value = "SELECT * FROM stock ORDER BY created_at DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Stock> findWithOffsetLimit(@Param("offset") int offset, @Param("limit") int limit);

}
