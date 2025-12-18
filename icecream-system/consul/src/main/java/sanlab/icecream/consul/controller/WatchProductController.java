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
import sanlab.icecream.consul.model.ProductESearch;
import sanlab.icecream.fundamentum.dto.core.FeedbackDto;
import sanlab.icecream.fundamentum.dto.core.ProductDto;
import sanlab.icecream.fundamentum.dto.exntended.ProductExtendedDto;
import sanlab.icecream.consul.exception.HttpInternalServerErrorException;
import sanlab.icecream.consul.exception.HttpNotFoundException;
import sanlab.icecream.consul.exception.HttpServiceUnavailableException;
import sanlab.icecream.consul.service.ProductService;
import sanlab.icecream.fundamentum.contractmodel.request.CollectionQueryRequest;
import sanlab.icecream.fundamentum.contractmodel.response.CollectionQueryResponse;
import sanlab.icecream.fundamentum.exception.IcRuntimeException;

import java.util.List;
import java.util.UUID;

import static sanlab.icecream.consul.exception.ConsulErrorModel.FAIL_TO_PERSIST_DATA;
import static sanlab.icecream.consul.exception.ConsulErrorModel.PRODUCT_NOT_FOUND;
import static sanlab.icecream.fundamentum.constant.PreAuthorizedAuthExp.WATCHER;

@RestController
@RequestMapping("/watcher/product")
@PreAuthorize(WATCHER)
@RequiredArgsConstructor
public class WatchProductController {

    private final ProductService productService;

    @GetMapping
    public CollectionQueryResponse<ProductESearch> getAll(@ModelAttribute CollectionQueryRequest request) {
        return productService.getAll(request, false);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductExtendedDto> getById(@PathVariable UUID id) {
        try {
            var result = productService.getById(id);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @GetMapping("/{id}/feedbacks")
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

    @PostMapping
    public ResponseEntity<ProductExtendedDto> create(@Valid @RequestBody ProductDto requestBody) {
        try {
            var result = productService.create(requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            if (PRODUCT_NOT_FOUND.equals(error)) throw new HttpNotFoundException(ex);
            throw new HttpInternalServerErrorException(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductExtendedDto> update(@PathVariable UUID id,
                                                     @Valid @RequestBody ProductDto requestBody) {
        try {
            var result = productService.update(id, requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case PRODUCT_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<ProductExtendedDto> updateCategories(@PathVariable UUID id, @RequestBody List<UUID> requestBody) {
        try {
            var result = productService.setCategories(id, requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case PRODUCT_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PostMapping("/{id}/media")
    public ResponseEntity<ProductExtendedDto> updateMedia(@PathVariable UUID id,
                                                          @RequestPart("avatar") MultipartFile avatar,
                                                          @RequestPart("media") MultipartFile[] media) {
        try {
            var result = productService.setMedia(id, avatar, media);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case PRODUCT_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

    @PostMapping("/{id}/stocks")
    public ResponseEntity<ProductExtendedDto> updateStocks(@PathVariable UUID id, @RequestBody List<UUID> requestBody) {
        try {
            var result = productService.setStocks(id, requestBody);
            return ResponseEntity.ok(result);
        } catch (IcRuntimeException ex) {
            var error = ex.getError();
            throw switch (error) {
                case PRODUCT_NOT_FOUND -> new HttpNotFoundException(ex);
                case FAIL_TO_PERSIST_DATA -> new HttpServiceUnavailableException(ex);
                default -> new HttpInternalServerErrorException(ex);
            };
        }
    }

}
