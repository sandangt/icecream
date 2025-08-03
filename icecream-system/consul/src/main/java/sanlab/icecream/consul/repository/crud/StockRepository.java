package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, UUID> {

    List<Stock> findByIdIn(List<UUID> id);

    Optional<Stock> findFirstByOrderByCreatedAt();

    List<Stock> findAllByOrderByCreatedAt();

}
