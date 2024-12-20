package sanlab.icecream.frontier.controller.watcher;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sanlab.icecream.frontier.dto.core.CategoryDto;
import sanlab.icecream.frontier.dto.core.ProductDto;
import sanlab.icecream.frontier.dto.extended.CategoryExtendedDto;
import sanlab.icecream.frontier.service.CategoryService;
import sanlab.icecream.frontier.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.WATCHER;

@RestController
@RequestMapping("/watcher/categories")
@PreAuthorize(WATCHER)
@RequiredArgsConstructor
public class WatchCategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryExtendedDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryExtendedDto> getById(@PathVariable UUID id) {
        var result = categoryService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}/products")
    public CollectionQueryResponse<ProductDto> getAllProducts(@PathVariable UUID id,
                                                              @ModelAttribute CollectionQueryRequest request) {
        return categoryService.getAllProducts(id, request.getPageRequest());
    }

    @PostMapping
    public ResponseEntity<CategoryExtendedDto> create(@Valid @RequestBody CategoryDto request) {
        var result = categoryService.create(request);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryExtendedDto> update(@PathVariable UUID id, @Valid @RequestBody CategoryDto request) {
        var result = categoryService.update(id, request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/avatars")
    public ResponseEntity<CategoryExtendedDto> updateMedia(@PathVariable UUID id,
                                                           @RequestPart("avatar") MultipartFile avatar) {
        var result = categoryService.setAvatar(id, avatar);
        return ResponseEntity.ok(result);
    }
}
