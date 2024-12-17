package sanlab.icecream.frontier.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.frontier.model.Cart;

import java.util.UUID;

public interface ICartRepository extends JpaRepository<Cart, UUID> {}
