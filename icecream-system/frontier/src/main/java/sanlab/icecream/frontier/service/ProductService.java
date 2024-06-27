package sanlab.icecream.frontier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.frontier.exception.ItemNotFoundException;
import sanlab.icecream.frontier.exception.StoringDatabaseException;
import sanlab.icecream.frontier.mapper.IProductMapper;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.model.Product;
import sanlab.icecream.frontier.repository.ICategoryRepository;
import sanlab.icecream.frontier.repository.IProductRepository;
import sanlab.icecream.frontier.viewmodel.dto.ProductDto;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.frontier.viewmodel.response.ProductResponse;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static sanlab.icecream.frontier.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.frontier.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final IProductMapper productMapper;

    public CollectionQueryResponse<ProductResponse> getProducts(Pageable pageable) {
        Page<Product> paginatedProducts = productRepository.findAll(pageable);
        long total = productRepository.count();
        List<ProductResponse> productList = productMapper.entityToResponse(paginatedProducts.stream().toList());
        return CollectionQueryResponse.<ProductResponse>builder()
            .total(total)
            .page(pageable.getPageNumber())
            .totalPages(calculateTotalPage(total, pageable.getPageSize()))
            .data(productList)
            .build();
    }

    public Optional<ProductResponse> getProductById(UUID id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(productMapper::entityToResponse);
    }

    public ProductResponse createProduct(ProductDto request) {
        try {
            Product result = productRepository.save(productMapper.dtoToEntity(request));
            return productMapper.entityToResponse(result);
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when creating product");
        }
    }

    public ProductResponse updateProduct(UUID id, ProductDto request) {
        Product targetProduct = productRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Product with id %s not found".formatted(id)));
        Product sourceProduct = productMapper.dtoToEntity(request);
        copyNotNull(sourceProduct, targetProduct);
        try {
            Product result = productRepository.save(targetProduct);
            return productMapper.entityToResponse(result);
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when updating product");
        }
    }

    public Optional<ProductResponse> setCategories(UUID productId, List<UUID> categoryIdList) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            return Optional.empty();
        }
        Set<Category> categories = categoryIdList.stream()
            .map(categoryRepository::findById)
            .map(Optional::get).collect(Collectors.toSet());
        product.get().setCategories(categories);
        var result = productRepository.save(product.get());
        return Optional.ofNullable(productMapper.entityToResponse(result));
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

}
