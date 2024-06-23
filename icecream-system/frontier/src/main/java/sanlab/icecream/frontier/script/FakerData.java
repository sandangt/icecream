package sanlab.icecream.frontier.script;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sanlab.icecream.frontier.constant.EImageType;
import sanlab.icecream.frontier.constant.EProductStatus;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.model.Image;
import sanlab.icecream.frontier.model.Product;
import sanlab.icecream.frontier.repository.ICategoryRepository;
import sanlab.icecream.frontier.repository.IImageRepository;
import sanlab.icecream.frontier.repository.IProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static sanlab.icecream.frontier.constant.EProductStatus.AVAILABLE;
import static sanlab.icecream.frontier.constant.EProductStatus.UNAVAILABLE;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FakerData {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final IImageRepository imageRepository;
    private static final String FAKE_IMAGE_URL = "https://picsum.photos/200/300?blur";

    @Bean
    public Faker getFaker() {
        return new Faker();
    }

    @Bean
    CommandLineRunner seedData() {
        seedProduct(1000);
        seedCategory(8);
        seedImage(8326);
        seedProductCategory();
        seedProductImage();
        return args -> log.info("SEED DATA Done.");
    }

    private void seedProduct(int number) {
        if (productRepository.count() > 0) {
            return;
        }
        var faker = getFaker();

        var result = IntStream.range(0, number).mapToObj(ignore -> {
            long quantity = faker.number().numberBetween(0, 1_000_000L);
            EProductStatus status = quantity == 0 ? UNAVAILABLE : AVAILABLE;
            return Product.builder()
                .name(faker.funnyName().name())
                .description(faker.lorem().characters(5, 1000))
                .price(faker.number().randomDouble(2, 0, 10_000_000L))
                .status(status)
                .quantity(quantity)
                .isFeatured(false)
                .build();
        }).toList();
        productRepository.saveAll(result);
    }

    private void seedCategory(int number) {
        if (categoryRepository.count() > 0) {
            return;
        }
        var faker = getFaker();
        Set<String> existingNames = new HashSet<>();
        var result = IntStream.range(0, number).mapToObj(ignore -> {
            String name;
            Slugify slugMaker = Slugify.builder().build();
            do {
                name = faker.funnyName().name();
            } while (existingNames.contains(slugMaker.slugify(name)));
            existingNames.add(name);
            return new Category(name);
        }).toList();
        categoryRepository.saveAll(result);
    }

    private void seedImage(int number) {
        if (imageRepository.count() > 0) {
            return;
        }
        var faker = getFaker();
        var result = IntStream.range(0, number)
            .mapToObj(ignore -> Image.builder()
                .description(faker.lorem().characters(5, 1000))
                .relativePath(FAKE_IMAGE_URL)
                .type(EImageType.MEDIA).build()
            ).toList();
        imageRepository.saveAll(result);
    }

    private void seedProductCategory() {
        Optional<Product> firstProduct = productRepository.findFirstByOrderByName();
        if (firstProduct.isEmpty() || !firstProduct.get().getCategories().isEmpty()) {
            return;
        }
        var faker = getFaker();
        long totalCategories = categoryRepository.count();
        List<Category> categories = categoryRepository.findAll();
        List<Product> productList = productRepository.findAll();
        productList.forEach(product -> {
            int random = faker.random().nextInt(0, (int) totalCategories);
            product.setCategories(IntStream.range(0, random).mapToObj(categories::get).collect(Collectors.toSet()));
        });
        productRepository.saveAll(productList);
    }

    private void seedProductImage() {
        long totalProducts = productRepository.count();
        long totalImages = imageRepository.count();
        if (totalProducts >= totalImages) {
            return;
        }
        Optional<Product> firstProduct = productRepository.findFirstByOrderByName();
        if (firstProduct.isEmpty() || !firstProduct.get().getMedia().isEmpty()) {
            return;
        }
        var faker = getFaker();

        List<Product> products = productRepository.findAll();
        List<Image> images = imageRepository.findAllByOrderById();
        int currentIndex = 0;
        for (Product product: products) {
            Set<Image> imageSet = new HashSet<>();
            long numberOfImages = faker.number().numberBetween(1, (totalImages - currentIndex) / (totalProducts - products.indexOf(product)));
            for (int i = 0; i < numberOfImages && currentIndex < totalImages; i++) {
                Image targetImage = images.get(currentIndex++);
                imageSet.add(targetImage);
            }
            product.setMedia(imageSet);
        }

        while (currentIndex < totalImages) {
            for (Product product: products) {
                Set<Image> imageSet = product.getMedia();
                if (currentIndex < totalImages) {
                    Image targetImage = images.get(currentIndex++);
                    imageSet.add(targetImage);
                    product.setMedia(imageSet);
                } else {
                    break;
                }
            }
        }
        productRepository.saveAll(products);
    }

}

