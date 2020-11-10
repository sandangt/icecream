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

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.entity.Category;
import com.IcecreamApp.service.ICategoryService;

@CrossOrigin
@RestController
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {        
    	return ResponseEntity.ok().body(categoryService.readAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("id") Long id) {
    	Optional<CategoryDTO> currentEntityWrapper = categoryService.readById(id);
    	if (currentEntityWrapper.isPresent())
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
    	this.categoryService.create(categoryDTO);
    	return ResponseEntity.ok().body(new MessageResponseDTO("Created new Category successfully"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
    	Optional<Category> currentEntityWrapper = categoryService.update(id, categoryDTO);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated Category successfully!"));
    	}
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> deleteCategory(@PathVariable("id") Long id) {
    	if (categoryService.delete(id)) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("Category item has been deleted successfully!"));
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }
    
    @GetMapping(params="search")
    public ResponseEntity<List<CategoryDTO>> searchCategoriesByName(@RequestParam("search") String categoryname) {
    	return ResponseEntity.ok().body(categoryService.searchCategoriesByName(categoryname));
    }
}
