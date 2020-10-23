package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.repository.CategoryRepository;

@Service
public class CategoryService extends GeneralService<Category, CategoryRepository> {

	public CategoryService(CategoryRepository repository) {
		super(repository);
	}

	@Override
	public ResponseEntity<Category> update(long id, Category entity) {

		Optional<Category> currentEntityWrapper = this.repository.findById(id);

	    if (!currentEntityWrapper.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    entity.setForeignKey(currentEntityWrapper.get());
	    this.repository.saveAndFlush(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}