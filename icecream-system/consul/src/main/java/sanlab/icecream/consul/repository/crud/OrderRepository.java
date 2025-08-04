package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {}
