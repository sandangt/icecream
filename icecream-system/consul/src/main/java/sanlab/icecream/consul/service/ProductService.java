package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.consul.dto.core.FeedbackDto;
import sanlab.icecream.consul.dto.core.ImageDto;
import sanlab.icecream.consul.dto.extended.ProductExtendedDto;
import sanlab.icecream.consul.mapper.FeedbackMapper;
import sanlab.icecream.consul.mapper.ImageMapper;
import sanlab.icecream.consul.mapper.ProductMapper;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.model.Image;
import sanlab.icecream.consul.model.Product;
import sanlab.icecream.consul.model.Stock;
import sanlab.icecream.consul.repository.crud.CategoryRepository;
import sanlab.icecream.consul.repository.crud.ProductRepository;
import sanlab.icecream.consul.dto.core.ProductDto;
import sanlab.icecream.consul.repository.crud.StockRepository;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.PRODUCT_NOT_FOUND;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ImageService imageService;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StockRepository stockRepository;

    private final FeedbackMapper feedbackMapper;
    private final ProductMapper productMapper;
    private final ImageMapper imageMapper;

    @Transactional(readOnly = true)
    public CollectionQueryResponse<ProductExtendedDto> getAll(Pageable pageable, boolean featuredOnly) {
        Page<Product> paginatedProducts = featuredOnly ?
            productRepository.findAllByIsFeaturedTrue(pageable) :
            productRepository.findAll(pageable);
        long total = featuredOnly ? productRepository.countByIsFeaturedTrue() : productRepository.count();
        List<ProductExtendedDto> productList = productMapper.entityToExtendedDto(paginatedProducts.stream().toList());
        return CollectionQueryResponse.<ProductExtendedDto>builder()
            .total(total)
            .page(pageable.getPageNumber())
            .totalPages(calculateTotalPage(total, pageable.getPageSize()))
            .data(productList)
            .build();
    }

    @Transactional(readOnly = true)
    public ProductExtendedDto getById(UUID id) {
        return productRepository.findById(id)
            .map(productMapper::entityToExtendedDto)
            .orElseThrow(() -> new IcRuntimeException(PRODUCT_NOT_FOUND, "id: %s".formatted(id)));
    }

    @Transactional(readOnly = true)
    public ProductExtendedDto getBySlug(String slug) {
        return productRepository.findFirstBySlug(slug)
            .map(productMapper::entityToExtendedDto)
            .orElseThrow(() -> new IcRuntimeException(PRODUCT_NOT_FOUND, "slug: %s".formatted(slug)));
    }

    @Transactional(readOnly = true)
    public CollectionQueryResponse<FeedbackDto> getAllFeedbacks(UUID productId, Pageable pageable) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IcRuntimeException(PRODUCT_NOT_FOUND, "id: %s".formatted(productId)));
        long total = product.getFeedbacks().size();
        List<FeedbackDto> feedbackList = product.getFeedbacks().parallelStream()
            .map(feedbackMapper::entityToDto)
            .toList();
        return CollectionQueryResponse.<FeedbackDto>builder()
            .total(total)
            .page(pageable.getPageNumber())
            .totalPages(calculateTotalPage(total, pageable.getPageSize()))
            .data(feedbackList)
            .build();
    }

    @Transactional
    public ProductExtendedDto create(ProductDto request) {
        try {
            Product product = productRepository.save(productMapper.dtoToEntity(request));
            return productMapper.entityToExtendedDto(product);
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "product");
        }
    }

    @Transactional
    public ProductExtendedDto update(UUID id, ProductDto request) {
        Product targetProduct = productRepository.findById(id)
            .orElseThrow(() -> new IcRuntimeException(PRODUCT_NOT_FOUND, "id: %s".formatted(id)));
        try {
            Product sourceProduct = productMapper.dtoToEntity(request);
            copyNotNull(sourceProduct, targetProduct);
            return productMapper.entityToExtendedDto(productRepository.save(targetProduct));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "product");
        }
    }

    @Transactional
    public ProductExtendedDto setCategories(UUID productId, List<UUID> categoryIdList) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IcRuntimeException(PRODUCT_NOT_FOUND, "id: %s".formatted(productId)));
        Set<Category> categories = categoryRepository.findByIdIn(categoryIdList).stream()
            .filter(category -> Optional.ofNullable(category).isPresent())
            .collect(Collectors.toSet());
        try {
            product.setCategories(categories);
            return productMapper.entityToExtendedDto(productRepository.save(product));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "setting product's categories");
        }
    }

    @Transactional
    public ProductExtendedDto setMedia(UUID productId, MultipartFile avatar, MultipartFile[] otherMedia) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IcRuntimeException(PRODUCT_NOT_FOUND, "id: %s".formatted(productId)));
        Deque<Image> savedDQ = new ArrayDeque<>();
        Optional.ofNullable(avatar).ifPresent(img -> {
            if (!img.isEmpty()) {
                var imageDto = imageService.upsertProductAvatar(productId, avatar);
                savedDQ.add(imageMapper.dtoToEntity(imageDto));
            }
        });
        if (otherMedia != null && otherMedia.length > 0) {
            List<ImageDto> imgList = imageService.bulkUpsertProductMedia(productId, otherMedia);
            savedDQ.addAll(imageMapper.dtoToEntity(imgList));
        }
        try {
            product.setMedia(new HashSet<>(savedDQ));
            return productMapper.entityToExtendedDto(productRepository.save(product));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "setting product's media");
        }
    }

    @Transactional
    public ProductExtendedDto setStocks(UUID productId, List<UUID> stockIdList) {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new IcRuntimeException(PRODUCT_NOT_FOUND, "id: %s".formatted(productId)));
        List<Stock> stocks = stockRepository.findByIdIn(stockIdList).stream()
            .filter(stock -> Optional.ofNullable(stock).isPresent())
            .toList();
        try {
            product.setStocks(stocks);
            return productMapper.entityToExtendedDto(productRepository.save(product));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, FAIL_TO_PERSIST_DATA, "setting product's stocks");
        }
    }

    // TODO
    @Transactional
    public void delete(UUID id) {
        Optional<Product> productOptional = productRepository.findById(id);
        productOptional.ifPresent(product -> {
            product.setCategories(null);
            product.setMedia(null);
            productRepository.deleteById(id);
        });
    }

}
