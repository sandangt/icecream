package sanlab.icecream.consul.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.service.ProductService;
import sanlab.icecream.consul.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.consul.viewmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.PRODUCT_NOT_FOUND;
import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.PERMIT_ALL;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    @PreAuthorize(PERMIT_ALL)
    public CollectionQueryResponse<ProductExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return productService.getAll(request.getPageRequest(), false);
    }

    @GetMapping("/featured")
    @PreAuthorize(PERMIT_ALL)
    public CollectionQueryResponse<ProductExtendedDto> getAllFeatured(@ModelAttribute CollectionQueryRequest request) {
        return productService.getAll(request.getPageRequest(), true);
    }

    @GetMapping("/{slug}")
    @PreAuthorize(PERMIT_ALL)
    public ResponseEntity<ProductExtendedDto> getBySlug(@PathVariable String slug) {
        try {
            var result = productService.getBySlug(slug);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @GetMapping("/{id}/feedbacks")
    @PreAuthorize(PERMIT_ALL)
    public CollectionQueryResponse<FeedbackDto> getAllFeedbacks(@PathVariable UUID id,
                                                                @ModelAttribute CollectionQueryRequest request) {
        try {
            return productService.getAllFeedbacks(id, request.getPageRequest());
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

}
