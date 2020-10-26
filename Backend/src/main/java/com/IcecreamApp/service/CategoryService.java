package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.CategoryRepository;

@Service
public class CategoryService extends GeneralService<Category, CategoryRepository> {
	
	public CategoryService(CategoryRepository repository) {
		super(repository);
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