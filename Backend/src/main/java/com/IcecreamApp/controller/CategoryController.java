package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.service.ICategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	
    @GetMapping
    public List<CategoryDTO> getAllCategories() {        
    	return categoryService.readAll();
    }

    @GetMapping(value = "/{id}")
    public CategoryDTO getCategoryById(@PathVariable("id") Long id) {
    	return categoryService.readById(id);
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
    	return this.categoryService.create(categoryDTO);
    }

    @PutMapping(value = "/{id}")
    public CategoryDTO updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
    	return this.categoryService.update(id, categoryDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCategory(@PathVariable("id") Long id) {
    	this.categoryService.delete(id);
    }
}
