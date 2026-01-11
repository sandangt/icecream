package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, CartItem.CartItemId> {}
