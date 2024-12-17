package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Stock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStockRepository extends JpaRepository<Stock, UUID> {

    List<Stock> findByIdIn(List<UUID> id);

    Optional<Stock> findFirstByOrderByCreatedAt();

    List<Stock> findAllByOrderByCreatedAt();

}
