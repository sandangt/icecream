package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Product;
import com.IcecreamApp.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

    @GetMapping
    public List<Product> getAllProducts() {        
    	return productService.readAll();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
    	return productService.readById(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
    	return this.productService.create(product);
    }

    @PutMapping(value = "/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
    	return this.productService.update(id, product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
    	this.productService.delete(id);
    }

}



