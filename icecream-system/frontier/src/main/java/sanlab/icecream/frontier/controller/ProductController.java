package sanlab.icecream.frontier.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sanlab.icecream.frontier.dto.extended.ProductExtendedDto;
import sanlab.icecream.frontier.service.ProductService;
import sanlab.icecream.frontier.dto.core.ProductDto;
import sanlab.icecream.frontier.viewmodel.request.CollectionQueryRequest;
import sanlab.icecream.frontier.viewmodel.response.CollectionQueryResponse;

import java.util.Optional;
import java.util.UUID;

import static sanlab.icecream.frontier.constant.PreAuthorizedRole.NORMIE;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get a list of products with pagination and sorting")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the products"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @GetMapping
    @PreAuthorize(NORMIE)
    public CollectionQueryResponse<ProductExtendedDto> getProducts(@ModelAttribute CollectionQueryRequest request) {
        return productService.getProducts(request.getPageRequest());
    }

    @Operation(summary = "Get a product by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the product"),
        @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductExtendedDto> getProductById(@Parameter(description = "ID of the product to be fetched") @PathVariable UUID id) {
        Optional<ProductExtendedDto> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Product created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request body"),
        @ApiResponse(responseCode = "500", description = "Error when creating product")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductExtendedDto> createProduct(@Parameter(description = "Product to be created") @Valid @RequestBody ProductDto request) {
        var result = productService.createProduct(request);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Update an existing product")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request body"),
        @ApiResponse(responseCode = "404", description = "Product not found"),
        @ApiResponse(responseCode = "500", description = "Error when updating product")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductExtendedDto> updateProduct(@Parameter(description = "ID of the product to be updated") @PathVariable UUID id,
                                                         @Parameter(description = "Updated product data") @Valid @RequestBody ProductDto request) {
        var result = productService.updateProduct(id, request);
        return ResponseEntity.ok(result);
    }

//    @Operation(summary = "Delete a product by its ID")
//    @ApiResponses(value = {
//        @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
//        @ApiResponse(responseCode = "404", description = "Product not found")
//    })
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteProduct(@Parameter(description = "ID of the product to be deleted") @PathVariable UUID id) {
//        productService.deleteProduct(id);
//    }

}
