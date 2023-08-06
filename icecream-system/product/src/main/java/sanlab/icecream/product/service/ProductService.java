package sanlab.icecream.product.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.product.exception.ErrorCode;
import sanlab.icecream.product.mapper.IMapper;
import sanlab.icecream.product.model.Category;
import sanlab.icecream.product.model.Product;
import sanlab.icecream.product.repository.ICategoryRepository;
import sanlab.icecream.product.repository.IProductRepository;
import sanlab.icecream.product.repository.lookup.MediaRepository;
import sanlab.icecream.sharedlib.exception.IllegalValueException;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.MediaDTO;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;
import sanlab.icecream.sharedlib.proto.ProductCategoryRelationship;
import sanlab.icecream.sharedlib.proto.ProductDTO;
import sanlab.icecream.sharedlib.proto.ProductResponse;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final MediaRepository mediaRepository;
    private final IMapper mapper;

    // region Helper methods
    private ProductResponse makeProductResponse(Product product) {
        ProductDTO productDTO = mapper.INSTANCE.modelToDTO(product);
        CategoryDTO categoryDTO = mapper.INSTANCE.modelToDTO(product.getCategory());
        ProductResponse.Builder builder = ProductResponse.newBuilder()
            .setProduct(productDTO)
            .setCategory(categoryDTO);
        mediaRepository
            .getMediaById(product.getMediaId())
            .ifPresent(builder::setMedia);
        return builder.build();
    }
    // endregion

    public List<ProductResponse> getAllProducts(PageInfoRequest pageInfo) {
        Pageable page = PageRequest.of(
            pageInfo.getOffset() / pageInfo.getLimit(),
            pageInfo.getLimit()
        );
        return productRepository.findAllByOrderByLastModifiedOnDesc(page)
            .stream()
            .map(this::makeProductResponse)
            .toList();
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Product with id: %s not found", id), ErrorCode.PRODUCT_NOT_FOUND
                )
            );
        return makeProductResponse(product);
    }

    @Transactional
    public List<ProductResponse> getProductListFromCategoryId(Long categoryId) throws ItemNotFoundException {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Category with id %s not found", categoryId), ErrorCode.CATEGORY_NOT_FOUND
                )
            );
        return category.getProductList()
            .stream()
            .map(this::makeProductResponse)
            .toList();
    }

    public void insertProduct(ProductDTO productDTO) {
        productRepository.save(mapper.INSTANCE.dtoToModel(productDTO));
    }

    public void updateProduct(ProductDTO productDTO) throws ItemNotFoundException {
        Product product = productRepository.findById(productDTO.getId())
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Product with ID: %s not found", productDTO.getId()), ErrorCode.PRODUCT_NOT_FOUND
                )
            );
        mapper.INSTANCE.updateProductFromDTO(productDTO, product);
        productRepository.save(product);
    }

    public void modifiedRelationship(ProductCategoryRelationship relationship) throws ItemNotFoundException {
        Long productId = relationship.getProductId();
        Long categoryId = relationship.getCategoryId();
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Product with ID: %s not found", productId), ErrorCode.PRODUCT_NOT_FOUND
                )
            );

        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Category with ID: %s not found", categoryId), ErrorCode.CATEGORY_NOT_FOUND
                )
            );
        if (relationship.getInRelationship()) {
            labelProduct(product, category);
            return;
        }
        unlabelProduct(product, category);
    }

    private void labelProduct(Product product, Category category) {
        product.setCategory(category);
        productRepository.save(product);
    }

    private void unlabelProduct(Product product, Category category) throws IllegalValueException {
        if (!category.equals(product.getCategory())) {
            throw new IllegalValueException(
                String.format("Product id %s has not been assigned to category id %s",
                    product.getId(), category.getId()),
                ErrorCode.LABEL_MISMATCH
            );
        }
        product.setCategory(null);
        productRepository.save(product);
    }
}
