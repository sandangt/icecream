package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.converter.CategoryConverter;
import com.IcecreamApp.entity.Category;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.CategoryRepository;
import com.IcecreamApp.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository repository;

	Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	private String entityName = "Category";
	
	@Override
	public List<CategoryDTO> readAll() {
		
		List<CategoryDTO> categories = new ArrayList<>();
		
    	for (Category i : repository.findAll()) {
    		categories.add(CategoryConverter.toDTO(i));
    	}
    	return categories;
	}

	@Override
	public CategoryDTO readById(long id) {
    	Category category = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
    	return CategoryConverter.toDTO(category);
	}

	@Override
	public CategoryDTO create(CategoryDTO categoryDTO) {
		repository.save(CategoryConverter.toEntity(categoryDTO));
		return categoryDTO;
	}

	@Override
	public CategoryDTO update(long id, CategoryDTO categoryDTO) {

		Optional<Category> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		Category category = CategoryConverter.toEntity(categoryDTO);
		category.setForeignKey(currentEntityWrapper.get());
		this.repository.save(category);
		return categoryDTO;
	}

	@Override
	public void delete(long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		this.repository.deleteById(id);
	}
}