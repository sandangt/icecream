package sanlab.icecream.consul.controller;

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
import sanlab.icecream.fundamentum.dto.core.CategoryDto;
import sanlab.icecream.fundamentum.dto.core.ProductDto;
import sanlab.icecream.fundamentum.dto.exntended.CategoryExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.service.CategoryService;
import sanlab.icecream.consul.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.CATEGORY_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
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
        try {
            var result = categoryService.getById(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var err = ex.getError();
            if (CATEGORY_NOT_FOUND.equals(err)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @GetMapping("/{id}/products")
    public CollectionQueryResponse<ProductDto> getAllProducts(@PathVariable UUID id,
                                                              @ModelAttribute CollectionQueryRequest request) {
        return categoryService.getAllProducts(id, request.getPageRequest());
    }

    @PostMapping
    public ResponseEntity<CategoryExtendedDto> create(@Valid @RequestBody CategoryDto request) {
        try {
            var result = categoryService.create(request);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var err = ex.getError();
            if (FAIL_TO_PERSIST_DATA.equals(err)) throw new HttpServiceUnavailableException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryExtendedDto> update(@PathVariable UUID id, @Valid @RequestBody CategoryDto request) {
        try {
            var result = categoryService.update(id, request);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CATEGORY_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PostMapping("/{id}/avatars")
    public ResponseEntity<CategoryExtendedDto> updateMedia(@PathVariable UUID id,
                                                           @RequestPart("avatar") MultipartFile avatar) {
        try {
            var result = categoryService.setAvatar(id, avatar);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case CATEGORY_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }
}
