package sanlab.icecream.frontier.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sanlab.icecream.frontier.exception.ItemNotFoundException;
import sanlab.icecream.frontier.exception.StoringDatabaseException;
import sanlab.icecream.frontier.mapper.ICategoryMapper;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.repository.ICategoryRepository;
import sanlab.icecream.frontier.viewmodel.dto.CategoryDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;


    public CategoryDto createCategory(CategoryDto request) {
        try {
            var result = categoryRepository.save(categoryMapper.dtoToEntity(request));
            return categoryMapper.entityToDto(result);
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when creating category");
        }
    }

    public CategoryDto updateCategory(UUID id, CategoryDto request) {
        Category targetCategory = categoryRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException("Category with id %s not found".formatted(id)));
        targetCategory.setName(request.getName());
        try {
            var result = categoryRepository.save(targetCategory);
            return categoryMapper.entityToDto(result);
        } catch (Exception ignore) {
            throw new StoringDatabaseException("Error occurs when updating category");
        }
    }

}
