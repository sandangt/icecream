package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.Cart;

import java.util.UUID;

public interface ICartRepository extends JpaRepository<Cart, UUID> {}
