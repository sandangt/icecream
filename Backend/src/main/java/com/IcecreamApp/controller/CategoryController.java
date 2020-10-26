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

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

    @GetMapping
    public List<Category> getAllCategories() {        
    	return categoryService.readAll();
    }

    @GetMapping(value = "/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) {
    	return categoryService.readById(id);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
    	return this.categoryService.create(category);
    }

    @PutMapping(value = "/{id}")
    public Category updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
    	return this.categoryService.update(id, category);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
    	this.categoryService.delete(id);
    }
}



