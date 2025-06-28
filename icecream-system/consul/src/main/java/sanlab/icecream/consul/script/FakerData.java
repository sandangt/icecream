package sanlab.icecream.consul.script;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sanlab.icecream.consul.model.Address;
import sanlab.icecream.consul.model.Stock;
import sanlab.icecream.consul.repository.crud.IAddressRepository;
import sanlab.icecream.consul.repository.crud.IStockRepository;
import sanlab.icecream.consul.service.ImageService;
import sanlab.icecream.consul.viewmodel.request.IcMultipartFileRequest;
import sanlab.icecream.fundamentum.constant.EImageType;
import sanlab.icecream.fundamentum.constant.EProductStatus;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.repository.crud.ICategoryRepository;
import sanlab.icecream.consul.repository.crud.IImageRepository;
import sanlab.icecream.consul.repository.crud.IProductRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static sanlab.icecream.fundamentum.constant.EProductStatus.AVAILABLE;
import static sanlab.icecream.fundamentum.constant.EProductStatus.UNAVAILABLE;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FakerData {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final IImageRepository imageRepository;
    private final IAddressRepository addressRepository;
    private final IStockRepository stockRepository;

    private final ImageService imageService;

    @Value("${app.temp-dir:''}")
    private String tmpDir;

    private static final String LOREM_IMAGE_ENTITY_PATH = "lorem";
    private static final String IMAGE_EXT = "jpg";
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
        return args -> {
            var tmpPath = Path.of(tmpDir);
            if (Files.exists(tmpPath)) FileUtils.deleteDirectory(tmpPath.toFile());
            log.info("SEED DATA Done.");
        };
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
                .briefDescription(faker.lorem().sentence(20))
                .description(faker.lorem().paragraph(5))
                .price(faker.number().randomDouble(2, 0, 10_000_000L))
                .status(status)
                .isFeatured(false)
                .sku(faker.lorem().characters(30))
                .stockQuantity(faker.number().randomNumber())
                .metaTitle(faker.lorem().characters(3, 20))
                .metaKeyword(faker.lorem().characters(3, 10))
                .metaDescription(faker.lorem().paragraph(5))
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
        IntStream.range(0, number).forEach(ignore -> {
            try {
                generateImage();
            } catch (IOException ignored) {}
        });
    }

    private Image generateImage() throws IOException {
        var faker = getFaker();
        String fileName = "%s.%s".formatted(UUID.randomUUID(), IMAGE_EXT);
        Path filePath = Path.of(LOREM_IMAGE_ENTITY_PATH, fileName);
        var imageDto = imageService.upsertImage(filePath,
            genRandomImg(400, 400, filePath.toString()),
            faker.lorem().sentence(10));
        var image = Image.builder()
            .description(imageDto.getDescription())
            .relativePath(imageDto.getRelativePath())
            .type(EImageType.MEDIA).build();
        return imageRepository.save(image);
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
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            try {
                Image img = generateImage();
                category.setAvatar(img);
            } catch (IOException ignored) {}
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

    private IcMultipartFileRequest genRandomImg(int width, int height, String relativeFilePath) throws IOException {
        var faker = getFaker();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color bgColor = new Color((int)(Math.random() * 0x1000000));
        g.setColor(bgColor);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 28));
        faker.lorem().words(6);
        String productName = "#%s".formatted(faker.lorem().word());
        g.drawString(productName, 20, height / 2);
        g.setFont(new Font("SansSerif", Font.PLAIN, 20));
        String sku = "SKU-%s".formatted(faker.lorem().words(6));
        g.drawString(sku, 20, height / 2 + 30);
        g.dispose();
        Path filePath = Path.of(tmpDir, relativeFilePath);
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        File file = filePath.toFile();
        ImageIO.write(img, "jpg", file);
        return new IcMultipartFileRequest(file);
    }

}

