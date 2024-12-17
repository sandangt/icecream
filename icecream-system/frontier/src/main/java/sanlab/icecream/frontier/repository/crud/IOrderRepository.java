package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Order;

import java.util.UUID;

public interface IOrderRepository extends JpaRepository<Order, UUID> {}
