package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.OrderItem;

import java.util.UUID;

public interface IOrderItemRepository extends JpaRepository<OrderItem, UUID> {}
