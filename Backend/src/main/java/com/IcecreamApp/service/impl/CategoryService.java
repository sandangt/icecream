package com.IcecreamApp.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.CategoryRepository;
import com.IcecreamApp.service.ICategoryService;

@Service
public class CategoryService extends GeneralService<Category, CategoryRepository> implements ICategoryService {
	Logger log = LoggerFactory.getLogger((CategoryService.class));
	public CategoryService() {
		this.entityName = "category";
	}

	@Override
	public Category update(long id, Category entity) {

//		Optional<Category> currentEntityWrapper = this.repository.findById(id);
//
//	    if (!currentEntityWrapper.isPresent()) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    entity.setForeignKey(currentEntityWrapper.get());
//	    this.repository.saveAndFlush(entity);
//		return new ResponseEntity<>(entity, HttpStatus.OK);
		
		Optional<Category> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		entity.setForeignKey(currentEntityWrapper.get());
		return this.repository.save(entity);
	}
}