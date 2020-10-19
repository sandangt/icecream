package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Product;
import com.IcecreamApp.service.ProductService;

@RestController
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

    @GetMapping(value = "/products")
    public ResponseEntity<List<Product>> getAllProducts() {        
    	return productService.readAll();
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
    	return productService.readById(id);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    	return this.productService.create(product);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
    	return this.productService.update(id, product);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
    	return this.productService.delete(id);
    }

}



