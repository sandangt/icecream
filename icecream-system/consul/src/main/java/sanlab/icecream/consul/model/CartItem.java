package sanlab.icecream.consul.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;
import static sanlab.icecream.fundamentum.constant.TableName.CART_ITEM;

@Entity
@Table(name = CART_ITEM)
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@IdClass(CartItem.CartItemId.class)
public class CartItem extends AbstractAuditEntity {

    private Long quantity;

    @Id
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Id
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem that = (CartItem) o;
        return cart != null && product != null &&
            cart.getId().equals(that.cart.getId()) &&
            product.getId().equals(that.product.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    public static class CartItemId implements Serializable {

        private UUID cart;
        private UUID product;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CartItemId)) return false;
            CartItemId that = (CartItemId) o;
            return Objects.equals(cart, that.cart) &&
                Objects.equals(product, that.product);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cart, product);
        }
    }

}
