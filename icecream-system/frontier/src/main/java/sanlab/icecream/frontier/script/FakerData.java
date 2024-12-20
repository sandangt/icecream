package sanlab.icecream.frontier.script;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sanlab.icecream.frontier.model.Address;
import sanlab.icecream.frontier.model.Stock;
import sanlab.icecream.frontier.repository.crud.IAddressRepository;
import sanlab.icecream.frontier.repository.crud.IStockRepository;
import sanlab.icecream.fundamentum.constant.EImageType;
import sanlab.icecream.fundamentum.constant.EProductStatus;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.model.Image;
import sanlab.icecream.frontier.model.Product;
import sanlab.icecream.frontier.repository.crud.ICategoryRepository;
import sanlab.icecream.frontier.repository.crud.IImageRepository;
import sanlab.icecream.frontier.repository.crud.IProductRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static sanlab.icecream.fundamentum.constant.EProductStatus.AVAILABLE;
import static sanlab.icecream.fundamentum.constant.EProductStatus.UNAVAILABLE;

//@Configuration
@Slf4j
@RequiredArgsConstructor
public class FakerData {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final IImageRepository imageRepository;
    private final IAddressRepository addressRepository;
    private final IStockRepository stockRepository;

    private static final String FAKE_IMAGE_RELATIVE_PATH = "/200/300?blur";
    private final Slugify slugMaker = Slugify.builder().build();

    @Bean
    public Faker getFaker() {
        return new Faker();
    }

    @Bean
    CommandLineRunner seedData() {
        seedImage(8326);
        seedAddress(7512);
        seedCategory(8);
        seedProduct(1000);
        seedStock(3101);
        seedProductImage();
        seedProductCategory();
        seedCategoryImage();
        seedStockAddress();
        seedProductStock();
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
            String name = faker.funnyName().name();
            return Product.builder()
                .slug(slugMaker.slugify(name))
                .name(name)
                .briefDescription(faker.lorem().characters(5, 30))
                .description(faker.lorem().characters(5, 1000))
                .price(faker.number().randomDouble(2, 0, 10_000_000L))
                .status(status)
                .isFeatured(false)
                .sku(faker.lorem().characters(30))
                .stockQuantity(faker.number().randomNumber())
                .metaTitle(faker.lorem().characters(3, 20))
                .metaKeyword(faker.lorem().characters(3, 10))
                .metaDescription(faker.lorem().characters(5, 30))
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
            do {
                name = faker.funnyName().name();
            } while (existingNames.contains(slugMaker.slugify(name)));
            existingNames.add(name);
            return new Category(name, faker.lorem().characters(5, 50));
        }).toList();
        categoryRepository.saveAll(result);
    }

    private void seedImage(int number) {
        if (imageRepository.count() > 0) {
            return;
        }
        var faker = getFaker();
        var result = IntStream.range(0, number)
            .mapToObj(ignore -> {
                String relativeFilePath = IntStream.range(0, 2)
                    .mapToObj(ignore1 -> faker.lorem().characters(1, 10)).collect(Collectors.joining("/"));
                return Image.builder()
                        .description(faker.lorem().characters(5, 1000))
                        .relativePath(relativeFilePath)
                        .type(EImageType.MEDIA).build();
                }
            ).toList();
        imageRepository.saveAll(result);
    }

    private void seedAddress(int number) {
        if (addressRepository.count() > 0) {
            return;
        }
        var faker = getFaker();
        var result = IntStream.range(0, number).mapToObj(ignore -> Address.builder()
            .contactName(faker.funnyName().name())
            .phone(faker.phoneNumber().cellPhone())
            .addressLine1(faker.address().fullAddress())
            .addressLine2(faker.address().fullAddress())
            .city(faker.address().city())
            .zipCode(faker.address().zipCode())
            .district(faker.address().streetName())
            .stateOrProvince(faker.address().cityName())
            .country(faker.address().country())
            .build()
        ).toList();
        addressRepository.saveAll(result);
    }

    private void seedStock(int number) {
        if (stockRepository.count() > 0) {
            return;
        }
        var faker = getFaker();
        var result = IntStream.range(0, number)
            .mapToObj(ignore -> Stock.builder()
                .quantity(faker.number().numberBetween(1L, 999_999_999L))
                .reservedQuantity(faker.number().numberBetween(1L, 999_999_999L))
                .build()
        ).toList();
        stockRepository.saveAll(result);
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
        if (totalProducts > totalImages) {
            return;
        }
        Optional<Product> firstProduct = productRepository.findFirstByOrderByName();
        var firstProductMediaOptional = firstProduct.map(Product::getMedia);
        if (firstProductMediaOptional.isPresent() && !firstProductMediaOptional.get().isEmpty()) {
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

    private void seedCategoryImage() {
        Optional<Category> firstCategory = categoryRepository.findFirstByOrderByName();
        if (firstCategory.map(Category::getAvatar).isPresent()) {
            return;
        }
        var faker = getFaker();
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            Image avatar = imageRepository.save(Image.builder()
                .description(faker.lorem().characters(5, 1000))
                .relativePath(FAKE_IMAGE_RELATIVE_PATH)
                .type(EImageType.MEDIA).build());
            category.setAvatar(avatar);
        }
        categoryRepository.saveAll(categories);
    }

    private void seedStockAddress() {
        long totalStocks = stockRepository.count();
        long totalAddresses = addressRepository.count();
        if (totalStocks > totalAddresses) {
            return;
        }
        Optional<Stock> firstStock = stockRepository.findFirstByOrderByCreatedAt();
        var firstStockAddressOptional = firstStock.map(Stock::getAddresses);
        if (firstStockAddressOptional.isPresent() && !firstStockAddressOptional.get().isEmpty()) {
            return;
        }
        var faker = getFaker();
        List<Stock> stocks = stockRepository.findAll();
        List<Address> addresses = addressRepository.findAllByOrderByCreatedAt();
        int currentIndex = 0;
        for (Stock stock : stocks) {
            Set<Address> addressSet = new HashSet<>();
            long numberOfAddresses = faker.number().numberBetween(1,
                (totalAddresses - currentIndex) / (totalAddresses - stocks.indexOf(stock)));
            for (int i=0; i < numberOfAddresses && currentIndex < totalAddresses; i++) {
                Address targetAddress = addresses.get(currentIndex++);
                addressSet.add(targetAddress);
            }
            stock.setAddresses(addressSet);
        }
        while (currentIndex < totalAddresses) {
            for (Stock stock : stocks) {
                Set<Address> addressSet = stock.getAddresses();
                if (currentIndex < totalAddresses) {
                    Address targetAddress = addresses.get(currentIndex++);
                    addressSet.add(targetAddress);
                    stock.setAddresses(addressSet);
                } else {
                    break;
                }
            }
        }
        stockRepository.saveAll(stocks);
    }

    private void seedProductStock() {
        long totalProducts = productRepository.count();
        long totalStocks = stockRepository.count();
        if (totalProducts > totalStocks) {
            return;
        }
        Optional<Product> firstProduct = productRepository.findFirstByOrderByName();
        var firstProductStockOptional = firstProduct.map(Product::getStocks);
        if (firstProductStockOptional.isPresent() && !firstProductStockOptional.get().isEmpty()) {
            return;
        }
        var faker = getFaker();
        List<Product> products = productRepository.findAll();
        List<Stock> stocks = stockRepository.findAllByOrderByCreatedAt();
        int currentIndex = 0;
        for (Product product : products) {
            List<Stock> stockList = new ArrayList<>();
            long numberOfStocks = faker.number().numberBetween(1,
                (totalStocks - currentIndex) / (totalStocks - products.indexOf(product)));
            for (int i=0; i < numberOfStocks && currentIndex < totalStocks; i++) {
                Stock targetStock = stocks.get(currentIndex++);
                stockList.add(targetStock);
            }
            stockList.forEach(stock -> stock.setProduct(product));
        }
        while (currentIndex < totalStocks) {
            for (Product product : products) {
                List<Stock> stockList = product.getStocks();
                if (currentIndex < totalStocks) {
                    Stock targetStock = stocks.get(currentIndex++);
                    stockList.add(targetStock);
                    stockList.forEach(stock -> stock.setProduct(product));
                }
            }
        }
        stockRepository.saveAll(stocks);
    }

}

