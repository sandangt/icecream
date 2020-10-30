package com.IcecreamApp.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Product;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.ProductRepository;
import com.IcecreamApp.service.IProductService;

@Service
public class ProductService extends GeneralService<Product, ProductRepository> implements IProductService {

	public ProductService() {
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
