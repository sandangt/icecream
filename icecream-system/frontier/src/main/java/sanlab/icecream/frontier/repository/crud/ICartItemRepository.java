package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.CartItem;

import java.util.UUID;

public interface ICartItemRepository extends JpaRepository<CartItem, UUID> {}
