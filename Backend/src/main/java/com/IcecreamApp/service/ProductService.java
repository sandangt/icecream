package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Product;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.ProductRepository;

@Service
public class ProductService extends GeneralService<Product, ProductRepository> {

	public ProductService(ProductRepository repository) {
		super(repository);
		entityName = "product";
	}

	@Override
	public Product update(long id, Product entity) {

//		Optional<Product> currentEntityWrapper = this.repository.findById(id);
//
//	    if (!currentEntityWrapper.isPresent()) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    entity.setForeignKey(currentEntityWrapper.get());
//	    this.repository.saveAndFlush(entity);
//		return new ResponseEntity<>(entity, HttpStatus.OK);
		
		Optional<Product> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		entity.setForeignKey(currentEntityWrapper.get());
		return this.repository.save(entity);
	}
}
