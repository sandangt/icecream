package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.converter.ProductConverter;
import com.IcecreamApp.entity.Product;
import com.IcecreamApp.repository.ProductRepository;
import com.IcecreamApp.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository repository;
	private String entityName = "Product";
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Override
	public List<ProductDTO> readAll() {    	
    	return repository.findAll().stream().map(ProductConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> readById(long id) {
		Optional<Product> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) 
			return Optional.ofNullable(ProductConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public Product create(ProductDTO productDTO) {
		return repository.save(ProductConverter.toEntity(productDTO));
	}	
	
	@Override
	public Optional<Product> update(long id, ProductDTO productDTO) {

		Optional<Product> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			Product product = ProductConverter.toEntity(productDTO);
			product.setForeignKey(currentEntityWrapper.get());
			return Optional.ofNullable(repository.save(product));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}
	
	@Override
	public boolean delete(long id) {
		Optional<Product> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}
}
