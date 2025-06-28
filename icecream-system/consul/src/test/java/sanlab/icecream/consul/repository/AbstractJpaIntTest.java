package sanlab.icecream.consul.repository;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import sanlab.icecream.fundamentum.constant.ECustomerStatus;
import sanlab.icecream.fundamentum.constant.EDeliveryMethod;
import sanlab.icecream.fundamentum.constant.EDeliveryStatus;
import sanlab.icecream.fundamentum.constant.EImageType;
import sanlab.icecream.fundamentum.constant.EOrderStatus;
import sanlab.icecream.fundamentum.constant.EPaymentMethod;
import sanlab.icecream.fundamentum.constant.EPaymentStatus;
import sanlab.icecream.fundamentum.constant.EProductStatus;
import sanlab.icecream.consul.model.Address;
import sanlab.icecream.consul.model.Cart;
import sanlab.icecream.consul.model.CartItem;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.model.Feedback;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.model.Order;
import sanlab.icecream.consul.model.OrderItem;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.model.Stock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.EProductStatus.AVAILABLE;
import static sanlab.icecream.fundamentum.constant.EProductStatus.UNAVAILABLE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class AbstractJpaIntTest {


    @ServiceConnection
    protected static final PostgreSQLContainer<?> POSTGRESQL_CONTAINER =
        new PostgreSQLContainer<>(DockerImageName.parse("postgres:16.3-alpine"));
    protected static final Slugify SLUG_BUILDER = Slugify.builder().build();
    protected static final Faker FAKER = new Faker();

    @BeforeAll
    protected static void containerUp() {
        POSTGRESQL_CONTAINER.start();
    }

    @AfterAll
    protected static void containerDown() {
        POSTGRESQL_CONTAINER.stop();
    }

    protected static List<Customer> generateCustomer(int number) {
        List<Customer> customers = new ArrayList<>();
        Set<UUID> existingId = new HashSet<>();
        for (int i=0; i<number; i++) {
            UUID id;
            do {
                id = UUID.randomUUID();
            } while (existingId.contains(id));
            existingId.add(id);
            String username = SLUG_BUILDER.slugify(FAKER.funnyName().name());
            customers.add(Customer.builder()
                .userId(id)
                .email(FAKER.internet().emailAddress())
                .username(username)
                .phone(FAKER.phoneNumber().cellPhone())
                .firstName(FAKER.name().firstName())
                .lastName(FAKER.name().firstName())
                .status(ECustomerStatus.ACTIVE)
                .build());
        }
        return customers;
    }

    protected static List<Product> generateProduct(int number) {
        List<Product> products = new ArrayList<>();
        for (int i=0; i<number; i++) {
            long quantity = FAKER.number().numberBetween(0, 1_000_000L);
            EProductStatus status = quantity == 0 ? UNAVAILABLE : AVAILABLE;
            String name = FAKER.funnyName().name();
            String description = FAKER.lorem().characters(5, 1000);
            products.add(Product.builder()
                .name(name)
                .slug(SLUG_BUILDER.slugify(name))
                .briefDescription(FAKER.lorem().characters(5, 30))
                .description(description)
                .status(status)
                .price(FAKER.number().randomDouble(2, 0, 10_000_000L))
                .sku(FAKER.lorem().characters(50))
                .isFeatured(false)
                .stockQuantity(FAKER.number().randomNumber())
                .metaTitle(name)
                .metaKeyword(FAKER.lorem().characters(10))
                .metaDescription(description)
                .build());
        }
        return products;
    }

    protected static List<Category> generateCategory(int number) {
        List<Category> categories = new ArrayList<>();
        Set<String> existingNames = new HashSet<>();
        for (int i=0; i<number; i++) {
            String name;
            do {
                name = FAKER.funnyName().name();
            } while (existingNames.contains(SLUG_BUILDER.slugify(name)));
            existingNames.add(name);
            categories.add(new Category(name, FAKER.lorem().characters(5, 1000)));
        }
        return categories;
    }

    protected static List<Image> generateImage(int number) {
        List<Image> images = new ArrayList<>();
        for (int i=0; i<number; i++) {
            images.add(Image.builder()
                .description(FAKER.lorem().characters(5, 1000))
                .relativePath(FAKER.file().fileName())
                .type(EImageType.MEDIA)
                .build());
        }
        return images;
    }

    protected static List<Address> generateAddress(int number) {
        List<Address> addresses = new ArrayList<>();
        for (int i=0; i<number; i++) {
            addresses.add(Address.builder()
                .contactName(FAKER.funnyName().name())
                .phone(FAKER.phoneNumber().cellPhone())
                .addressLine1(FAKER.address().streetAddress())
                .addressLine2(FAKER.address().streetAddress())
                .city(FAKER.address().city())
                .zipCode(FAKER.address().zipCode())
                .district(FAKER.lorem().characters(5, 30))
                .stateOrProvince(FAKER.address().streetName())
                .country(FAKER.address().country())
                .build());
        }
        return addresses;
    }

    protected static List<Stock> generateStock(int number) {
        List<Stock> stocks = new ArrayList<>();
        for (int i=0; i<number; i++) {
            stocks.add(Stock.builder()
                .quantity(FAKER.number().randomNumber())
                .reservedQuantity(FAKER.number().randomNumber())
                .build());
        }
        return stocks;
    }

    protected static List<Feedback> generateFeedback(int number) {
        List<Feedback> feedbacks = new ArrayList<>();
        for (int i=0; i<number; i++) {
            feedbacks.add(Feedback.builder()
                .content(FAKER.lorem().characters(10, 100))
                .star(FAKER.number().numberBetween(1, 5))
                .build());
        }
        return feedbacks;
    }

    protected static List<Order> generateOrder(int number) {
        List<Order> orders = new ArrayList<>();
        for (int i=0; i<number; i++) {
            double totalPrice = FAKER.number().randomDouble(2, 0, 999_999_999);
            double discount = FAKER.number().randomDouble(2, 0, 1) * totalPrice;
            double deliveryFee = FAKER.number().randomDouble(2, 0, 1) * totalPrice;
            orders.add(Order.builder()
                .note(FAKER.lorem().characters(0, 1000))
                .discount(discount)
                .totalPrice(totalPrice)
                .deliveryFee(deliveryFee)
                .deliveryMethod(FAKER.options().option(EDeliveryMethod.class))
                .paymentMethod(FAKER.options().option(EPaymentMethod.class))
                .orderStatus(FAKER.options().option(EOrderStatus.class))
                .deliveryStatus(FAKER.options().option(EDeliveryStatus.class))
                .paymentStatus(FAKER.options().option(EPaymentStatus.class))
                .build());
        }
        return orders;
    }

    protected static List<OrderItem> generateOrderItem(int number) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i=0; i<number; i++) {
            double price = FAKER.number().randomDouble(2, 0, 999_999_999);;
            double discount = FAKER.number().randomDouble(2, 0, 1) * price;
            orderItems.add(OrderItem.builder()
                .quantity(FAKER.number().randomNumber())
                .note(FAKER.lorem().characters(10, 2000))
                .price(price)
                .discount(discount)
                .build());
        }
        return orderItems;
    }

    protected static List<Cart> generateCart(int number) {
        List<Cart> carts = new ArrayList<>();
        for (int i=0; i<number; i++) {
            carts.add(new Cart());
        }
        return carts;
    }

    protected static List<CartItem> generateCartItem(int number) {
        List<CartItem> cartItems = new ArrayList<>();
        for (int i=0; i<number; i++) {
            cartItems.add(CartItem.builder()
                .quantity(FAKER.number().randomNumber())
                .build());
        }
        return cartItems;
    }

}
