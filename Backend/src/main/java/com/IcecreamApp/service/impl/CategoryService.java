package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.converter.CategoryConverter;
import com.IcecreamApp.entity.Category;
import com.IcecreamApp.repository.CategoryRepository;
import com.IcecreamApp.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	private String entityName = "Category"; 
	
	@Override
	public List<CategoryDTO> readAll() {
    	
    	return repository.findAll().stream().map(CategoryConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<CategoryDTO> readById(long id) {
		Category category = repository.findById(id).get();
		return Optional.ofNullable(CategoryConverter.toDTO(category));
	}

	@Override
	public Category create(CategoryDTO categoryDTO) {
		return repository.save(CategoryConverter.toEntity(categoryDTO));
	}

	@Override
	public Optional<Category> update(long id, CategoryDTO categoryDTO) {
		Optional<Category> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			Category category = CategoryConverter.toEntity(categoryDTO);
			category.setForeignKey(currentEntityWrapper.get());
			return Optional.ofNullable(this.repository.save(category));
		}
		logger.error(String.format("%s id %ld not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<Category> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %ld not found", entityName, id));
		return false;
	}
}