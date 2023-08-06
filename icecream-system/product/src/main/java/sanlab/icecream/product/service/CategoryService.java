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
import sanlab.icecream.product.repository.lookup.MediaRepository;
import sanlab.icecream.sharedlib.exception.ItemNotFoundException;
import sanlab.icecream.sharedlib.proto.CategoryDTO;
import sanlab.icecream.sharedlib.proto.CategoryResponse;
import sanlab.icecream.sharedlib.proto.CategoryResponseCollection;
import sanlab.icecream.sharedlib.proto.MediaDTO;
import sanlab.icecream.sharedlib.proto.ProductDTO;


@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ICategoryRepository categoryRepository;
    private final IProductRepository productRepository;
    private final MediaRepository mediaRepository;
    private final IMapper mapper;

    // region Helper methods
    private CategoryResponse makeCategoryResponse(Category category) {
        CategoryDTO categoryDTO = mapper.INSTANCE.modelToDTO(category);
        List<ProductDTO> productDTOList = category.getProductList()
            .stream()
            .map(mapper.INSTANCE::modelToDTO)
            .toList();
        CategoryResponse.Builder builder = CategoryResponse.newBuilder()
            .setCategory(categoryDTO)
            .addAllProduct(productDTOList);
        mediaRepository
            .getMediaById(category.getMediaId())
            .ifPresent(builder::setMedia);
        return builder.build();
    }
    // endregion

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAllByOrderByNameAsc()
            .stream()
            .map(this::makeCategoryResponse)
            .toList();
    }

    public CategoryResponse getCategoryById(Long id) throws ItemNotFoundException {
        Optional<Category> categoryWrapper = categoryRepository.findById(id);
        Category category = categoryWrapper.orElseThrow(() -> new ItemNotFoundException(
                String.format("Category with id %s not found", id), ErrorCode.CATEGORY_NOT_FOUND
            )
        );
        return makeCategoryResponse(category);
    }

    @Transactional
    public CategoryResponse getCategoryByProductId(Long productId) throws ItemNotFoundException {
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new ItemNotFoundException(
                String.format("Product with id %s not found", productId), ErrorCode.PRODUCT_NOT_FOUND
            )
        );
        Category category;
        if ((category = product.getCategory()) == null) {
            throw new ItemNotFoundException(
                String.format("Category for product id %s not found", productId), ErrorCode.CATEGORY_NOT_FOUND
            );
        }
        return makeCategoryResponse(category);
    }

    public void insertCategory(CategoryDTO categoryDTO) {
        categoryRepository.save(mapper.INSTANCE.dtoToModel(categoryDTO));
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
