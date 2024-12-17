package sanlab.icecream.frontier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.frontier.dto.core.ProductDto;
import sanlab.icecream.frontier.dto.extended.CategoryExtendedDto;
import sanlab.icecream.fundamentum.exception.ItemNotFoundException;
import sanlab.icecream.fundamentum.exception.StoringDatabaseException;
import sanlab.icecream.frontier.mapper.ICategoryMapper;
import sanlab.icecream.frontier.mapper.IImageMapper;
import sanlab.icecream.frontier.mapper.IProductMapper;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.repository.crud.ICategoryRepository;
import sanlab.icecream.frontier.dto.core.CategoryDto;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static sanlab.icecream.fundamentum.utils.ObjectUtils.copyNotNull;
import static sanlab.icecream.fundamentum.utils.RequestUtils.calculateTotalPage;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ImageService imageService;
    private final ICategoryRepository categoryRepository;

    private final ICategoryMapper categoryMapper;
    private final IImageMapper imageMapper;
    private final IProductMapper productMapper;

    public List<CategoryExtendedDto> getAll() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.entityToExtendedDto(categoryList);
    }

    public CategoryExtendedDto getById(UUID id) {
        return categoryRepository.findById(id)
            .map(categoryMapper::entityToExtendedDto)
            .orElseThrow(() -> ItemNotFoundException.category(id));
    }

    public CategoryExtendedDto create(CategoryDto request) {
        try {
            var result = categoryRepository.save(categoryMapper.dtoToEntity(request));
            return categoryMapper.entityToExtendedDto(result);
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when creating category");
        }
    }

    public CategoryExtendedDto update(UUID id, CategoryDto request) {
        Category targetCategory = categoryRepository.findById(id)
            .orElseThrow(() -> ItemNotFoundException.category(id));
        Category sourceCategory = categoryMapper.dtoToEntity(request);
        copyNotNull(sourceCategory, targetCategory);
        try {
            return categoryMapper.entityToExtendedDto(categoryRepository.save(targetCategory));
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when updating category");
        }
    }

    public CategoryExtendedDto setAvatar(UUID id, MultipartFile avatar) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> ItemNotFoundException.category(id));
        Optional.ofNullable(avatar).ifPresent(img -> {
            if (!img.isEmpty()) {
                var imageDto = imageService.upsertCategoryAvatar(id, avatar);
                category.setAvatar(imageMapper.dtoToEntity(imageDto));
            }
        });
        return categoryMapper.entityToExtendedDto(categoryRepository.save(category));
    }

    public CollectionQueryResponse<ProductDto> getAllProducts(UUID categoryId, Pageable pageable) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> ItemNotFoundException.category(categoryId));
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

}
