package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.OrderItem;

import java.util.UUID;

public interface IOrderItemRepository extends JpaRepository<OrderItem, UUID> {}
