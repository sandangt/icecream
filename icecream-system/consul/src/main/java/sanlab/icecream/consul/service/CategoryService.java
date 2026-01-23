package sanlab.icecream.consul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.fundamentum.dto.core.ProductDto;
import sanlab.icecream.fundamentum.dto.exntended.CategoryExtendedDto;
import sanlab.icecream.consul.mapper.CategoryMapper;
import sanlab.icecream.consul.mapper.ImageMapper;
import sanlab.icecream.consul.mapper.ProductMapper;
import sanlab.icecream.consul.model.Category;
import sanlab.icecream.consul.repository.crud.CategoryRepository;
import sanlab.icecream.fundamentum.dto.core.CategoryDto;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CATEGORY_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ImageService imageService;
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    private final ImageMapper imageMapper;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<CategoryExtendedDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.entityToExtendedDto(categoryList);
    }

    @Transactional(readOnly = true)
    public CategoryExtendedDto getById(UUID id) {
        return categoryRepository.findById(id)
            .map(categoryMapper::entityToExtendedDto)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CATEGORY_NOT_FOUND, "id: %s".formatted(id)));
    }

    @Transactional(readOnly = true)
    public CollectionQueryResponse<ProductDto> getAllProducts(UUID categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CATEGORY_NOT_FOUND, "id: %s".formatted(categoryId)));
        long total = category.getProducts().size();
        List<ProductDto> productList = category.getProducts().parallelStream()
            .map(productMapper::entityToDto)
            .toList();
        return CollectionQueryResponse.<ProductDto>builder()
            .total(total)
            .page(pageable.getPageNumber())
            .totalPages(calculateTotalPage(total, pageable.getPageSize()))
            .data(productList)
            .build();
    }

    @Transactional(readOnly = true)
    public List<ProductExtendedDto> getAllProducts(UUID categoryId) {
        if (!categoryRepository.existsById(categoryId))
            throw new IcRuntimeException(REPOSITORY_CATEGORY_NOT_FOUND, "id: %s".formatted(categoryId));
        return categoryRepository.findAllProductsById(categoryId)
            .parallelStream()
            .map(productMapper::entityToExtendedDto)
            .toList();
    }

    @Transactional
    public CategoryExtendedDto create(CategoryDto request) {
        try {
            var result = categoryRepository.save(categoryMapper.dtoToEntity(request));
            return categoryMapper.entityToExtendedDto(result);
        } catch (Exception ignored) {
            throw new IcRuntimeException(REPOSITORY_PERSIST_DATA_FAILED, "category");
        }
    }

    @Transactional
    public CategoryExtendedDto update(UUID id, CategoryDto request) {
        Category targetCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CATEGORY_NOT_FOUND, "id: %s".formatted(id)));
        try {
            Category sourceCategory = categoryMapper.dtoToEntity(request);
            copyNotNull(sourceCategory, targetCategory);
            return categoryMapper.entityToExtendedDto(categoryRepository.save(targetCategory));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "category");
        }
    }

    @Transactional
    public CategoryExtendedDto setAvatar(UUID id, MultipartFile avatar) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new IcRuntimeException(REPOSITORY_CATEGORY_NOT_FOUND, "id: %s".formatted(id)));
        try {
            Optional.ofNullable(avatar).ifPresent(img -> {
                if (!img.isEmpty()) {
                    var imageDto = imageService.upsertCategoryAvatar(id, avatar);
                    category.setAvatar(imageMapper.dtoToEntity(imageDto));
                }
            });
            return categoryMapper.entityToExtendedDto(categoryRepository.save(category));
        } catch (Exception ex) {
            throw new IcRuntimeException(ex, REPOSITORY_PERSIST_DATA_FAILED, "saving category's avatar");
        }
    }

}
