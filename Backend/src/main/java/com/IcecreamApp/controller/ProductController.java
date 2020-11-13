package com.IcecreamApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.PageDTO;
import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.entity.Product;
import com.IcecreamApp.service.IProductService;

@CrossOrigin
@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private IProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {        
    	return ResponseEntity.ok().body(productService.readAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
    	Optional<ProductDTO> currentEntityWrapper = productService.readById(id);
    	if (currentEntityWrapper.isPresent())
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
	
    @GetMapping(params={"page","offset"})
    public ResponseEntity<PageDTO<ProductDTO>> getProductsByPage(@RequestParam("page") int page, @RequestParam("offset") int offset) {
    	return ResponseEntity.ok().body(productService.readByPage(page, offset));
    }
    
    @GetMapping(params="search")
    public ResponseEntity<List<ProductDTO>> searchProductsByName(@RequestParam("search") String productname) {
    	return ResponseEntity.ok().body(productService.searchProductsByName(productname));
    }
    
    @GetMapping(value="/{id}/feedbacks")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByProduct(@PathVariable("id") long id) {
    	return ResponseEntity.ok().body(productService.readFeedbacksByProduct(id));
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createProduct(@RequestBody ProductDTO productDTO) {
    	productService.create(productDTO);
    	return ResponseEntity.ok().body(new MessageResponseDTO("Created new Product successfully"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
    	Optional<Product> currentEntityWrapper = productService.update(id, productDTO);
    	if (currentEntityWrapper.isPresent())
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated Product successfully!"));
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> deleteProduct(@PathVariable("id") Long id) {
    	if (productService.delete(id))
    		return ResponseEntity.ok().body(new MessageResponseDTO("Product item has been deleted successfully!"));
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }

}



