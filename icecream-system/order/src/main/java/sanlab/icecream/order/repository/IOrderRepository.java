package sanlab.icecream.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sanlab.icecream.order.model.Order;


@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {}
