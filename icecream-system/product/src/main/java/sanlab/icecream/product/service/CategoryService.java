package sanlab.icecream.product.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.product.exception.ErrorCode;
import sanlab.icecream.product.mapper.IMapper;
import sanlab.icecream.product.model.Category;
import sanlab.icecream.product.model.Product;
import sanlab.icecream.product.repository.ICategoryRepository;
import sanlab.icecream.product.repository.IProductRepository;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CategoryDTO;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;
    private final IMapper mapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAllByOrderByNameAsc()
            .stream()
            .map(mapper.INSTANCE::modelToDTO)
            .toList();
    }

    public CategoryDTO getCategoryById(Long id) throws ItemNotFoundException {
        Optional<Category> categoryWrapper = categoryRepository.findById(id);
        Category category = categoryWrapper.orElseThrow(() -> new ItemNotFoundException(
                String.format("Category with id %s not found", id), ErrorCode.CATEGORY_NOT_FOUND
            )
        );
        return mapper.INSTANCE.modelToDTO(category);
    }

    @Transactional
    public CategoryDTO getCategoryByProductId(Long productId) throws ItemNotFoundException {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Product with id %s not found", productId), ErrorCode.PRODUCT_NOT_FOUND
            )
        );
        return mapper.INSTANCE.modelToDTO(product.getCategory());
    }

    public void insertCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(mapper.INSTANCE.DTOToModel(categoryDTO));
    }

    public void updateCategory(CategoryDTO categoryDTO) throws ItemNotFoundException {
        Category category = categoryRepository.findById(categoryDTO.getId())
            .orElseThrow(() -> new ItemNotFoundException(
                    String.format("Category with ID: %s not found", categoryDTO.getId()), ErrorCode.CATEGORY_NOT_FOUND
                )
            );
        mapper.INSTANCE.updateCategoryFromDTO(categoryDTO, category);
        categoryRepository.save(category);
    }
}
