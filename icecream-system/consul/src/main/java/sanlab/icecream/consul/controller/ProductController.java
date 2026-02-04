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
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.exception.HttpUnauthorizedException;
import sanlab.icecream.consul.service.FeedbackService;
import sanlab.icecream.consul.utils.CollectionQueryUtils;
import sanlab.icecream.consul.utils.SecurityContextUtils;
import sanlab.icecream.fundamentum.dto.aggregated.FeedbackStatDto;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.exntended.FeedbackExtendedDto;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.service.ProductService;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_CUSTOMER_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_FEEDBACK_NOT_FOUND;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PERSIST_DATA_FAILED;
import static sanlab.icecream.consul.exception.ConsulErrorModel.REPOSITORY_PRODUCT_NOT_FOUND;
import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.HAS_ROLE_NORMIE;
import static sanlab.icecream.fundamentum.constant.EPreAuthorizeRole.PERMIT_ALL;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final FeedbackService feedbackService;

    @GetMapping
    @PreAuthorize(PERMIT_ALL)
    public CollectionQueryResponse<ProductExtendedDto> getAll(@ModelAttribute CollectionQueryRequest request) {
        return productService.getAll(request, false);
    }

    @GetMapping("/featured")
    @PreAuthorize(PERMIT_ALL)
    public CollectionQueryResponse<ProductExtendedDto> getAllFeatured(@ModelAttribute CollectionQueryRequest request) {
        return productService.getAll(request, true);
    }

    @GetMapping("/{slug}")
    @PreAuthorize(PERMIT_ALL)
    public ResponseEntity<ProductExtendedDto> getBySlug(@PathVariable String slug) {
        try {
            var result = productService.getBySlug(slug);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (REPOSITORY_PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @GetMapping("/{id}/feedbacks")
    @PreAuthorize(PERMIT_ALL)
    public CollectionQueryResponse<FeedbackExtendedDto> getAllFeedbacks(@PathVariable UUID id,
                                                                            @ModelAttribute CollectionQueryRequest request) {
        try {
            return feedbackService.getAllFeedbacks(id, CollectionQueryUtils.getPageRequest(request));
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (REPOSITORY_PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @GetMapping("/{id}/feedbacks/stat")
    @PreAuthorize(PERMIT_ALL)
    public ResponseEntity<FeedbackStatDto> getFeedbackStatByProductId(@PathVariable UUID id) {
        try {
            var result = feedbackService.getFeedbackStatByProductId(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (REPOSITORY_PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PostMapping("/{id}/feedbacks")
    @PreAuthorize(HAS_ROLE_NORMIE)
    public ResponseEntity<FeedbackExtendedDto> create(@PathVariable UUID id,
                                                    @Valid @RequestBody FeedbackDto requestBody) {
        try {
            var result = feedbackService.create(id, requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case REPOSITORY_CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case REPOSITORY_PRODUCT_NOT_FOUND -> new HttpNotFoundException(ex);
                case REPOSITORY_PERSIST_DATA_FAILED -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PutMapping("/{id}/feedbacks")
    @PreAuthorize(HAS_ROLE_NORMIE)
    public ResponseEntity<FeedbackExtendedDto> update(@PathVariable UUID id,
                                                      @Valid @RequestBody FeedbackDto requestBody) {
        try {
            var result = feedbackService.update(id, requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case REPOSITORY_CUSTOMER_NOT_FOUND -> new HttpUnauthorizedException(ex);
                case REPOSITORY_FEEDBACK_NOT_FOUND -> new HttpNotFoundException(ex);
                case REPOSITORY_PERSIST_DATA_FAILED -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
