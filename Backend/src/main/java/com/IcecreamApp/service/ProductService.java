package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Product;
import com.IcecreamApp.repository.ProductRepository;

@Service
public class ProductService extends GeneralService<Product, ProductRepository> {

	public ProductService(ProductRepository repository) {
		super(repository);
	}

	@Override
	public ResponseEntity<Product> update(long id, Product entity) {

		Optional<Product> currentEntityWrapper = this.repository.findById(id);

	    if (!currentEntityWrapper.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    entity.setForeignKey(currentEntityWrapper.get());
	    this.repository.saveAndFlush(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
