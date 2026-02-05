package sanlab.icecream.consul.fake;

import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.dto.keycloak.KeycloakCreateUserDto;
import sanlab.icecream.consul.model.Address;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.model.Customer;
import sanlab.icecream.consul.model.Feedback;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.model.Stock;
import sanlab.icecream.consul.repository.crud.AddressRepository;
import sanlab.icecream.consul.repository.crud.CategoryRepository;
import sanlab.icecream.consul.repository.crud.CustomerRepository;
import sanlab.icecream.consul.repository.crud.FeedbackRepository;
import sanlab.icecream.consul.repository.crud.ImageRepository;
import sanlab.icecream.consul.repository.crud.ProductRepository;
import sanlab.icecream.consul.repository.crud.StockRepository;
import sanlab.icecream.consul.repository.restclient.IdentityRepository;
import sanlab.icecream.consul.service.ImageService;
import sanlab.icecream.consul.viewmodel.request.IcMultipartFileRequest;
import sanlab.icecream.fundamentum.constant.ECustomerStatus;
import sanlab.icecream.fundamentum.constant.EImageType;
import sanlab.icecream.fundamentum.constant.EProductStatus;
import sanlab.icecream.fundamentum.utils.LogUtils;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_GARDENER;
import static sanlab.icecream.fundamentum.constant.EProductStatus.AVAILABLE;
import static sanlab.icecream.fundamentum.constant.EProductStatus.UNAVAILABLE;

@Slf4j
@RestController
@RequestMapping("/api/fake")
@RequiredArgsConstructor
@PreAuthorize(HAS_ROLE_GARDENER)
public class FakerController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final AddressRepository addressRepository;
    private final StockRepository stockRepository;
    private final CustomerRepository customerRepository;
    private final ImageService imageService;
    private final FeedbackRepository feedbackRepository;
    private final IdentityRepository identityRepository;
    private final Faker faker = new Faker();

    @Value("${app.temp-dir:''}")
    private String tmpDir;

    private static final String LOREM_IMAGE_ENTITY_PATH = "lorem";
    private static final String IMAGE_EXT = "jpg";
    private static final String SEED_USER_PASSWORD = "loremIpsumUser1234";
    private final Slugify slugMaker = Slugify.builder().build();

    @PostMapping("/seed")
    public ResponseEntity<Void> seedData() {
        seedCategory(8);
        seedProduct(1000);
        seedProductCategory();
        seedCustomer(300);
        return ResponseEntity.ok().build();
    }

    private void seedCategory(int number) {
        Set<String> existingNames = new HashSet<>();
        List<Category> categoryList = IntStream.range(0, number).mapToObj(ignore -> {
            String name;
            do {
                name = faker.funnyName().name();
            } while (existingNames.contains(slugMaker.slugify(name)));
            existingNames.add(name);
            return new Category(name, faker.lorem().characters(5, 50));
        }).toList();
        for (var category : categoryList) {
            try {
                Image img = generateImage(null);
                category.setAvatar(img);
            } catch (IOException ignored) {}
        }
        categoryRepository.saveAll(categoryList);
        LogUtils.logInfo(log, "=== seedCategory finished ===");
    }

    private void seedProduct(int number) {
        List<Product> productList = IntStream.range(0, number).mapToObj(ignore -> {
            long quantity = faker.number().numberBetween(0, 1_000_000L);
            EProductStatus status = quantity == 0 ? UNAVAILABLE : AVAILABLE;
            String name = faker.funnyName().name();
            return Product.builder()
                .slug(slugMaker.slugify(name))
                .name(name)
                .briefDescription(faker.lorem().sentence(20))
                .description(faker.lorem().paragraph(5))
                .price(faker.number().randomDouble(2, 0, 10_000_000L))
                .status(status.name())
                .isFeatured(false)
                .sku(faker.lorem().characters(30))
                .stockQuantity(faker.number().randomNumber())
                .metaTitle(faker.lorem().characters(3, 20))
                .metaKeyword(faker.lorem().characters(3, 10))
                .metaDescription(faker.lorem().paragraph(5))
                .build();
        }).map(Product.class::cast).toList();
        for (var product : productList) {
            //#region Generate images
            try {
                int imageNum = faker.number().numberBetween(1, 10);
                Image avatar = generateImage(EImageType.AVATAR);
                Set<Image> imgSet = IntStream.range(0, imageNum).mapToObj(ignore -> {
                    try {
                        return generateImage(null);
                    } catch (IOException _) {
                        return null;
                    }
                }).filter(Objects::nonNull).collect(Collectors.toSet());
                imgSet.add(avatar);
                product.setMedia(imgSet);
            } catch (IOException _) {}
            //#endregion
            //#region Generate stocks
            int stockNum = faker.number().numberBetween(1, 10);
            List<Stock> stockList = IntStream.range(0, stockNum)
                .mapToObj(ignore -> generateStock())
                .toList();
            product.setStocks(stockList);
            //#endregion
        }
        productRepository.saveAll(productList);
        LogUtils.logInfo(log, "=== seedProduct finished ===");
    }

    private void seedProductCategory() {
        long totalCategories = categoryRepository.count();
        List<Category> categories = categoryRepository.findAll();
        List<Product> productList = productRepository.findAll();
        productList.forEach(product -> {
            int random = faker.random().nextInt(0, (int) totalCategories);
            product.setCategories(IntStream.range(0, random).mapToObj(categories::get).collect(Collectors.toSet()));
        });
        productRepository.saveAll(productList);
    }

    private void seedCustomer(int number) {
        List<Customer> customerList = IntStream.range(0, number).mapToObj(ignore -> Customer.builder()
            .userId(UUID.randomUUID())
            .email(faker.internet().emailAddress())
            .username(faker.name().username())
            .phone(faker.phoneNumber().cellPhone())
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .status(ECustomerStatus.ACTIVE.name())
            .build()).map(Customer.class::cast).toList();
        List<UUID> productIds = productRepository.findAllId();
        List<UUID> productIdSubLst;
        for (var customer : customerList) {
            //#region Create keycloak user
            identityRepository.createUserUnverified(new KeycloakCreateUserDto.Tidy(
                customer.getUsername(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), SEED_USER_PASSWORD
            ));
            //#endregion
            //#region Generate image
            try {
                Image avatar = generateImage(EImageType.AVATAR);
                customer.setMedia(Set.of(avatar));
            } catch (IOException _) {}
            //#endregion
            //#region Generate addresses
            int addressNum = faker.number().numberBetween(1, 5);
            var addressSet = IntStream.range(0, addressNum).mapToObj(ignore -> generateAddress()).collect(Collectors.toSet());
            customer.setAddresses(addressSet);
            customer.setPrimaryAddress(addressSet.stream().findFirst().orElse(null));
            //#endregion
        }
        var savedCustomers = customerRepository.saveAll(customerList);
        for (var customer : savedCustomers) {
            int productNum = faker.number().numberBetween(1, productIds.size() - 2);
            productIdSubLst = new ArrayList<>(productIds);
            Collections.shuffle(productIdSubLst);
            List<Product> productLst = productRepository.findAllByIdIn(productIdSubLst.subList(0, productNum));
            productLst.forEach(product -> generateFeedback(customer, product));
        }
        LogUtils.logInfo(log, "=== seedCustomer finished ===");
    }

    private Stock generateStock() {
        var stock = Stock.builder()
            .quantity(faker.number().numberBetween(1L, 999_999_999L))
            .reservedQuantity(faker.number().numberBetween(1L, 999_999_999L))
            .build();
        var address = generateAddress();
        stock.setAddresses(Set.of(address));
        var result = stockRepository.save(stock);
        log.info("+++ generateStock finished +++");
        return result;
    }

    private Address generateAddress() {
        var address = Address.builder()
            .contactName(faker.funnyName().name())
            .phone(faker.phoneNumber().cellPhone())
            .addressLine1(faker.address().fullAddress())
            .addressLine2(faker.address().fullAddress())
            .city(faker.address().city())
            .zipCode(faker.address().zipCode())
            .district(faker.address().streetName())
            .stateOrProvince(faker.address().cityName())
            .country(faker.address().country())
            .build();
        var result = addressRepository.save(address);
        log.info("+++ generateAddress finished +++");
        return result;
    }

    private Feedback generateFeedback(Customer customer, Product product) {
        String content = faker.lorem().paragraph(5);
        int star = faker.number().numberBetween(1, 5);
        Feedback feedback = Feedback.builder()
            .content(content)
            .star(star)
            .customer(customer)
            .product(product)
            .build();
        var result = feedbackRepository.save(feedback);
        log.info("+++ generateFeedback finished +++");
        return result;
    }

    private Image generateImage(EImageType imageType) throws IOException {
        EImageType imgType = imageType;
        if (imageType == null) {
            imgType = EImageType.MEDIA;
        }
        String fileName = "%s.%s".formatted(UUID.randomUUID(), IMAGE_EXT);
        Path filePath = Path.of(LOREM_IMAGE_ENTITY_PATH, fileName);
        var imageDto = imageService.upsertImage(filePath,
            genRandomImg(400, 400, filePath.toString()),
            faker.lorem().sentence(10));
        var image = Image.builder()
            .description(imageDto.getDescription())
            .relativePath(imageDto.getRelativePath())
            .type(imgType.name()).build();
        var result = imageRepository.save(image);
        log.info("+++ generateImage finished +++");
        return result;
    }

    private IcMultipartFileRequest genRandomImg(int width, int height, String relativeFilePath) throws IOException {
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
