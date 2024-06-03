package sanlab.icecream.frontier.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.frontier.mapper.ICategoryMapper;
import sanlab.icecream.frontier.model.Category;
import sanlab.icecream.frontier.repository.ICategoryRepository;
import sanlab.icecream.frontier.service.CategoryService;
import sanlab.icecream.frontier.viewmodel.dto.CategoryDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public record CategoryController(CategoryService categoryService,
                                 ICategoryRepository categoryRepository,
                                 ICategoryMapper categoryMapper) {

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.entityToDto(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(categoryMapper::entityToDto).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto request) {
        var result = categoryService.createCategory(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryDto request) {
        var result = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(result);
    }

}
