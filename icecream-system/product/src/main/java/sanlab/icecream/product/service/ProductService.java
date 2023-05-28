package sanlab.icecream.product.service;

import java.util.List;

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
import sanlab.icecream.sharedlib.exception.IllegalValueException;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.PageInfoRequest;
import sanlab.icecream.sharedlib.proto.ProductCategoryRelationship;
import sanlab.icecream.sharedlib.proto.ProductDTO;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private final IMapper mapper;

    public List<ProductDTO> getAllProducts(PageInfoRequest pageInfo) {
        Pageable page = PageRequest.of(
            Math.round((float) pageInfo.getOffset() / (float) pageInfo.getLimit()),
            pageInfo.getLimit()
        );
        return productRepository.findAllByOrderByLastModifiedOnDesc(page)
            .stream()
            .map(mapper.INSTANCE::modelToDTO)
            .toList();
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Product with id: %s not found", id), ErrorCode.PRODUCT_NOT_FOUND
                )
            );
        return mapper.INSTANCE.modelToDTO(product);
    }

    @Transactional
    public List<ProductDTO> getProductListFromCategoryId(Long categoryId) throws ItemNotFoundException {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Category with id %s not found", categoryId), ErrorCode.CATEGORY_NOT_FOUND
                )
            );
        return category.getProductList()
            .stream()
            .map(mapper.INSTANCE::modelToDTO)
            .toList();
    }

    public void insertProduct(ProductDTO productDTO) {
        productRepository.save(mapper.INSTANCE.DTOToModel(productDTO));
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
