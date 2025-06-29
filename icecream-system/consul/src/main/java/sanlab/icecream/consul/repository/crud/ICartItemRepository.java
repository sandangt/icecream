package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.CartItem;

import java.util.UUID;

public interface ICartItemRepository extends JpaRepository<CartItem, UUID> {}
