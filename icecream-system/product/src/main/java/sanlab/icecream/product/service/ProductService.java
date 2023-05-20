package sanlab.icecream.product.service;

import java.util.List;

import icecream.sharedlib.proto.PageInfoRequest;
import icecream.sharedlib.proto.ProductDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sanlab.icecream.product.exception.ErrorCode;
import sanlab.icecream.product.mapper.ProductMapper;
import sanlab.icecream.product.model.Category;
import sanlab.icecream.product.model.Product;
import sanlab.icecream.product.repository.ICategoryRepository;
import sanlab.icecream.product.repository.IProductRepository;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;

    public List<ProductDTO> getAllProducts(PageInfoRequest pageInfo) {
        Pageable page = PageRequest.of(
            Math.round((float) pageInfo.getOffset() / (float) pageInfo.getLimit()),
            pageInfo.getLimit()
        );
        return productRepository.findAllByOrderByLastModifiedOnDesc(page)
            .stream()
            .map(ProductMapper::modelToDTO)
            .toList();
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Product with id: %s not found", id), ErrorCode.PRODUCT_NOT_FOUND
            )
        );
        return ProductMapper.modelToDTO(product);
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
            .map(ProductMapper::modelToDTO)
            .toList();
    }

    public void insertProduct(ProductDTO productDTO) {
        throw new NotImplementedException();
    }

    public void updateProduct(ProductDTO productDTO) {
        throw new NotImplementedException();
    }
}
