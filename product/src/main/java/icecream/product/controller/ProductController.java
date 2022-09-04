package icecream.product.controller;

import icecream.product.model.Product;
import icecream.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService productService;

    private ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok().body(productService.readAll());
    }

    @GetMapping(value = "/storefront")
    public ResponseEntity<List<Product>> getAllProductsForStorefront() {
        return getAllProducts();
    }

    @GetMapping(value = "/backoffice")
    public ResponseEntity<List<Product>> getAllProductsForBackoffice() {
        return getAllProducts();
    }
}
