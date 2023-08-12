package sanlab.icecream.gateway.service.product;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.gateway.exception.ErrorCode;
import sanlab.icecream.gateway.mapper.ILookupMapper;
import sanlab.icecream.gateway.mapper.IProductMapper;
import sanlab.icecream.gateway.repository.product.ProductRepository;
import sanlab.icecream.gateway.viewmodel.PageInfoRequestVm;
import sanlab.icecream.gateway.viewmodel.lookup.MediaVm;
import sanlab.icecream.gateway.viewmodel.product.CategoryVm;
import sanlab.icecream.gateway.viewmodel.product.ProductResponseVm;
import sanlab.icecream.gateway.viewmodel.product.ProductVm;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.ProductDTO;
import sanlab.icecream.sharedlib.proto.ProductResponse;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final IProductMapper productMapper;
    private final ILookupMapper lookupMapper;

    // region Helper methods
    private ProductResponseVm makeProductResponse(ProductResponse productResponse) {
        ProductVm productVm = productMapper.DTOToVm(productResponse.getProduct());
        CategoryVm categoryVm = productMapper.DTOToVm(productResponse.getCategory());
        MediaVm mediaVm = lookupMapper.DTOToVm(productResponse.getMedia());
        return new ProductResponseVm(productVm, categoryVm, mediaVm);
    }
    // endregion

    public List<ProductResponseVm> getAllProducts(PageInfoRequestVm pageInfo) {
        List<ProductResponse> productList = productRepository
            .getAllProducts(pageInfo)
            .orElse(Collections.emptyList());
        return productList.stream()
            .map(this::makeProductResponse)
            .toList();
    }

    public ProductResponseVm getProductById(Long id) throws ItemNotFoundException {
        ProductResponse productResponse = productRepository.getProductById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Product with ID %s not found", id), ErrorCode.PRODUCT_PRODUCT_NOT_FOUND
                )
            );
        return makeProductResponse(productResponse);
    }

    public void insertProduct(ProductVm productVm) {
        ProductDTO productDTO = productMapper.INSTANCE.VmToDTO(productVm);
        productRepository.insertProduct(productDTO);
    }

    public void updateProduct(ProductVm productVm) {
        ProductDTO productDTO = productMapper.INSTANCE.VmToDTO(productVm);
        productRepository.updateProduct(productDTO);
    }

    public void labelProduct(Long productId, Long categoryId) {
        productRepository.labelProduct(productId, categoryId);
    }

    public void unlabelProduct(Long productId, Long categoryId) {
        productRepository.unlabelProduct(productId, categoryId);
    }
}
