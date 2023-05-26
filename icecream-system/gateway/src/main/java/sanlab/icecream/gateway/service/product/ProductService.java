package sanlab.icecream.gateway.service.product;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.gateway.exception.ErrorCode;
import sanlab.icecream.gateway.mapper.IProductMapper;
import sanlab.icecream.gateway.repository.product.CategoryRepository;
import sanlab.icecream.gateway.repository.product.ProductRepository;
import sanlab.icecream.gateway.viewmodel.PageInfoRequestVm;
import sanlab.icecream.gateway.viewmodel.product.CategoryVm;
import sanlab.icecream.gateway.viewmodel.product.ProductResponseVm;
import sanlab.icecream.gateway.viewmodel.product.ProductVm;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.ProductDTO;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final IProductMapper productMapper;

    public List<ProductResponseVm> getAllProducts(PageInfoRequestVm pageInfo) {
        List<ProductDTO> productList = productRepository
            .getAllProducts(pageInfo)
            .orElse(Collections.emptyList());
        return productList.stream()
            .map(productMapper.INSTANCE::DTOToVm)
            .map(productVm -> new ProductResponseVm(productVm, getCategoryFromProductId(productVm.id())))
            .toList();
    }

    public ProductResponseVm getProductById(Long id) throws ItemNotFoundException {
        ProductDTO productDTO = productRepository.getProductById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Product with ID %s not found", id), ErrorCode.PRODUCT_PRODUCT_NOT_FOUND
                )
            );
        ProductVm productVm = productMapper.INSTANCE.DTOToVm(productDTO);
        CategoryVm categoryVm = getCategoryFromProductId(id);
        return new ProductResponseVm(productVm, categoryVm);
    }

    public void insertProduct(ProductVm productVm) {
        ProductDTO productDTO = productMapper.INSTANCE.VmToDTO(productVm);
        productRepository.insertProduct(productDTO);
    }

    public void updateProduct(ProductVm productVm) {
        ProductDTO productDTO = productMapper.INSTANCE.VmToDTO(productVm);
        productRepository.updateProduct(productDTO);
    }

    // region Helper methods
    private CategoryVm getCategoryFromProductId(Long productId) {
        CategoryDTO categoryDTO = categoryRepository.getCategoryFromProductId(productId).orElse(null);
        return productMapper.INSTANCE.DTOToVm(categoryDTO);
    }
    // endregion
}
