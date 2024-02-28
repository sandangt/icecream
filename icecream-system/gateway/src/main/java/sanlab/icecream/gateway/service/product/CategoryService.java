package sanlab.icecream.gateway.service.product;

import java.util.Collections;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.gateway.exception.ErrorCode;
import sanlab.icecream.gateway.mapper.ILookupMapper;
import sanlab.icecream.gateway.mapper.IProductMapper;
import sanlab.icecream.gateway.repository.product.CategoryRepository;
import sanlab.icecream.gateway.viewmodel.lookup.MediaVm;
import sanlab.icecream.gateway.viewmodel.product.CategoryResponseVm;
import sanlab.icecream.gateway.viewmodel.product.CategoryVm;
import sanlab.icecream.gateway.viewmodel.product.ProductVm;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.CategoryResponse;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final IProductMapper productMapper;
    private final ILookupMapper lookupMapper;

    // region Helper methods
    private CategoryResponseVm makeCategoryResponse(CategoryResponse categoryResponse) {
        CategoryVm categoryVm = productMapper.dtoToVm(categoryResponse.getCategory());
        List<ProductVm> productVmList = categoryResponse.getProductList()
            .stream()
            .map(productMapper.INSTANCE::dtoToVm)
            .toList();
        MediaVm mediaVm = lookupMapper.INSTANCE.dtoToVm(categoryResponse.getMedia());
        return new CategoryResponseVm(categoryVm, productVmList, mediaVm);
    }
    // endregion

    public List<CategoryResponseVm> getAllCategories() {
        return categoryRepository.getAllCategories()
            .orElse(Collections.emptyList())
            .stream()
            .map(this::makeCategoryResponse)
            .toList();
    }

    public CategoryResponseVm getCategoryById(Long id) throws ItemNotFoundException {
        CategoryResponse categoryResponse = categoryRepository.getCategoryById(id)
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Category with ID %s not found", id), ErrorCode.PRODUCT_CATEGORY_NOT_FOUND
                )
            );
        return makeCategoryResponse(categoryResponse);
    }

    public void insertCategory(CategoryVm categoryVm) {
        CategoryDTO categoryDTO = productMapper.INSTANCE.vmToDTO(categoryVm);
        categoryRepository.insertCategory(categoryDTO);
    }

    public void updateCategory(CategoryVm categoryVm) {
        CategoryDTO categoryDTO = productMapper.INSTANCE.vmToDTO(categoryVm);
        categoryRepository.updateCategory(categoryDTO);
    }
}
