package sanlab.icecream.gateway.service.product;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.gateway.exception.ErrorCode;
import sanlab.icecream.gateway.mapper.IProductMapper;
import sanlab.icecream.gateway.repository.product.CategoryRepository;
import sanlab.icecream.gateway.repository.product.ProductRepository;
import sanlab.icecream.gateway.viewmodel.product.CategoryResponseVm;
import sanlab.icecream.gateway.viewmodel.product.CategoryVm;
import sanlab.icecream.gateway.viewmodel.product.ProductVm;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CategoryDTO;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final IProductMapper productMapper;

    public List<CategoryResponseVm> getAllCategories() {
        return categoryRepository.getAllCategories()
            .orElse(Collections.emptyList())
            .stream()
            .map(productMapper.INSTANCE::DTOToVm)
            .map(categoryVm -> new CategoryResponseVm(
                    categoryVm,
                    getProductListFromCategoryId(categoryVm.id())
                )
            )
            .toList();
    }

    public CategoryResponseVm getCategoryById(Long id) throws ItemNotFoundException {
        CategoryDTO categoryDTO = categoryRepository.getCategoryById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Category with ID %s not found", id), ErrorCode.PRODUCT_CATEGORY_NOT_FOUND
                )
            );
        CategoryVm categoryVm = productMapper.INSTANCE.DTOToVm(categoryDTO);
        List<ProductVm> productList = getProductListFromCategoryId(id);
        return new CategoryResponseVm(categoryVm, productList);
    }

    public void insertCategory(CategoryVm categoryVm) {
        CategoryDTO categoryDTO = productMapper.INSTANCE.VmToDTO(categoryVm);
        categoryRepository.insertCategory(categoryDTO);
    }

    public void updateCategory(CategoryVm categoryVm) {
        CategoryDTO categoryDTO = productMapper.INSTANCE.VmToDTO(categoryVm);
        categoryRepository.updateCategory(categoryDTO);
    }

    // region Helper methods
    private List<ProductVm> getProductListFromCategoryId(Long categoryId) {
        return productRepository.getProductListFromCategoryId(categoryId)
            .orElse(Collections.emptyList())
            .stream()
            .map(productMapper.INSTANCE::DTOToVm)
            .toList();
    }
    // endregion
}
