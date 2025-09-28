package sanlab.icecream.consul.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import sanlab.icecream.consul.model.CartItem;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {

    List<CartItem> findAllByIdIn(List<UUID> ids);

    void deleteAllByCart_IdAndProduct_Id(UUID cartId, UUID productId);

    void deleteAllByIdIn(Collection<UUID> ids);

}
