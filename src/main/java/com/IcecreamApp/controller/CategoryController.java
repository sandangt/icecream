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

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.service.CategoryService;

@RestController
public class CategoryController {
	
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    @GetMapping(value = "/categories")
    public ResponseEntity<List<Category>> getAllCategories() {        
    	return categoryService.readAll();
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
    	return categoryService.readById(id);
    }

    @PostMapping(value = "/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    	return this.categoryService.create(category);
    }

    @PutMapping(value = "/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
    	return this.categoryService.update(id, category);
    }

    @DeleteMapping(value = "/categories/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") Long id) {
    	return this.categoryService.delete(id);
    }
}