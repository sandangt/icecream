package sanlab.icecream.product.service;

import java.util.List;
import java.util.Optional;

import icecream.sharedlib.proto.CategoryDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.product.exception.ErrorCode;
import sanlab.icecream.product.mapper.CategoryMapper;
import sanlab.icecream.product.model.Category;
import sanlab.icecream.product.model.Product;
import sanlab.icecream.product.repository.ICategoryRepository;
import sanlab.icecream.product.repository.IProductRepository;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAllByOrderByNameAsc()
            .stream()
            .map(CategoryMapper::modelToDTO)
            .toList();
    }

    public CategoryDTO getCategoryById(Long id) throws ItemNotFoundException {
        Optional<Category> categoryWrapper = categoryRepository.findById(id);
        Category category = categoryWrapper.orElseThrow(() -> new ItemNotFoundException(
                String.format("Category with id %s not found", id), ErrorCode.CATEGORY_NOT_FOUND
            )
        );
        return CategoryMapper.modelToDTO(category);
    }

    @Transactional
    public CategoryDTO getCategoryByProductId(Long productId) throws ItemNotFoundException {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Product with id %s not found", productId), ErrorCode.PRODUCT_NOT_FOUND
            )
        );
        return CategoryMapper.modelToDTO(product.getCategory());
    }
}
