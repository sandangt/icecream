package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.service.IProductService;

@CrossOrigin
@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private IProductService productService;

    @GetMapping
    public List<ProductDTO> getAllProducts() {        
    	return productService.readAll();
    }

    @GetMapping(value = "/{id}")
    public ProductDTO getProductById(@PathVariable("id") Long id) {
    	return productService.readById(id);
    }

    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
    	return this.productService.create(productDTO);
    }

    @PutMapping(value = "/{id}")
    public ProductDTO updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
    	return this.productService.update(id, productDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
    	this.productService.delete(id);
    }

}



