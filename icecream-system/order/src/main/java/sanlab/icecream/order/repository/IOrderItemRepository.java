package sanlab.icecream.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanlab.icecream.order.model.OrderItem;

@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {}
