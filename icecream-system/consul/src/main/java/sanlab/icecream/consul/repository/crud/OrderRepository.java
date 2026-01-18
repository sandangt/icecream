package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID>, OrderExtendRepository {

    Optional<Order> findFirstByCustomer_UserId(UUID userId);

    Optional<Order> findFirstByPaymentId(UUID paymentId);

}
