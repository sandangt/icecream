package sanlab.icecream.consul.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.model.Feedback;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.repository.crud.CategoryRepository;
import sanlab.icecream.consul.repository.crud.FeedbackRepository;
import sanlab.icecream.consul.repository.crud.ProductRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class IProductRepositoryTest extends AbstractJpaIntTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

    private static List<Product> productList = Collections.emptyList();
    private static List<Category> categoryList = Collections.emptyList();
    private static List<Feedback> feedbackList = Collections.emptyList();
    private static final int CATEGORIES_PER_PRODUCT = 3;
    private static final int FEEDBACK_PER_PRODUCT = 20;

    @BeforeEach
    void setUp() {
        productList = productRepository.saveAll(generateProduct(5));
        categoryList = categoryRepository.saveAll(generateCategory(10));
        feedbackList = feedbackRepository.saveAll(generateFeedback(100));
    }

    @Test
    void create() {
        assertThat(productRepository.count()).isEqualTo(productList.size());
        Product product = productList.getFirst();
        Optional<Product> dbProductOptional = productRepository.findById(product.getId());
        assertThat(dbProductOptional).isPresent().contains(product);
    }

    @Test
    void update() {
        Product product = productList.getFirst();
        String name = "Renamed Product";
        Double price = 9.99;
        product.setName(name);
        product.setPrice(price);
        product.setBriefDescription(null);
        product.setDescription(null);
        productRepository.save(product);
        Optional<Product> dbProductOptional = productRepository.findById(product.getId());
        assertThat(dbProductOptional).isPresent().contains(product);
    }

    @Test
    void addCategory() {
        for (Product product : productList) {
            Collections.shuffle(categoryList);
            product.setCategories(Set.copyOf(categoryList.subList(0, CATEGORIES_PER_PRODUCT)));
        }
        Optional<Product> dbProductOptional = productRepository.findById(productList.getFirst().getId());
        assertThat(dbProductOptional.map(Product::getCategories)).isPresent();
        Set<Category> dbCategories = dbProductOptional.map(Product::getCategories).get();
        assertThat(dbCategories).hasSize(CATEGORIES_PER_PRODUCT);
    }

    @Test
    void relate_image() {}

    @Test
    void addFeedback() {
        int offset = 0;
        for (Product product : productList) {
            product.setFeedbacks(feedbackList.subList(offset, offset + FEEDBACK_PER_PRODUCT));
            offset += FEEDBACK_PER_PRODUCT;
        }
        Optional<Product> dbProductOptional = productRepository.findById(productList.getFirst().getId());
        assertThat(dbProductOptional.map(Product::getFeedbacks)).isPresent();
        List<Feedback> dbFeedbacks = dbProductOptional.map(Product::getFeedbacks).get();
        assertThat(dbFeedbacks).hasSize(FEEDBACK_PER_PRODUCT);
        dbFeedbacks.remove(dbFeedbacks.getFirst());
        dbProductOptional = productRepository.findById(productList.getFirst().getId());
        assertThat(dbProductOptional.map(Product::getFeedbacks).get()).hasSize(FEEDBACK_PER_PRODUCT - 1);
    }

}

