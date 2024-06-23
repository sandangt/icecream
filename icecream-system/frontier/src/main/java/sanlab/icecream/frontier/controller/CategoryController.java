package sanlab.icecream.frontier.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import static sanlab.icecream.frontier.constant.PreAuthorizedRole.NORMIE_AND_WATCHER;
import static sanlab.icecream.frontier.constant.PreAuthorizedRole.WATCHER;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ICategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;

    @GetMapping
    @PermitAll
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryMapper.entityToDto(categoryList);
    }

    @GetMapping("/{id}")
    @PreAuthorize(NORMIE_AND_WATCHER)
    public ResponseEntity<CategoryDto> getCategory(@PathVariable UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(categoryMapper::entityToDto).map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @PreAuthorize(WATCHER)
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto request) {
        var result = categoryService.createCategory(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize(WATCHER)
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable UUID id, @Valid @RequestBody CategoryDto request) {
        var result = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(result);
    }

}
