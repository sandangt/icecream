package sanlab.icecream.consul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.fundamentum.dto.core.ProductDto;
import sanlab.icecream.fundamentum.dto.exntended.CategoryExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.service.CategoryService;
import sanlab.icecream.consul.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.CATEGORY_NOT_FOUND;
import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.PERMIT_ALL;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @PreAuthorize(PERMIT_ALL)
    public List<CategoryExtendedDto> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize(PERMIT_ALL)
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
    @PreAuthorize(PERMIT_ALL)
    public CollectionQueryResponse<ProductDto> getAllProducts(@PathVariable UUID id,
                                                              @ModelAttribute CollectionQueryRequest request) {
        try {
            return categoryService.getAllProducts(id, request.getPageRequest());
        } catch (IcRuntimeException ex) {
            var err = ex.getError();
            if (CATEGORY_NOT_FOUND.equals(err)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

}
