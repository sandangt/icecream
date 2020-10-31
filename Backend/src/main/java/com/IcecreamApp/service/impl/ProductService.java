package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.converter.ProductConverter;
import com.IcecreamApp.entity.Product;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.ProductRepository;
import com.IcecreamApp.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository repository;
	private String entityName = "Product";
	Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	@Override
	public List<ProductDTO> readAll() {
		
		List<ProductDTO> products = new ArrayList<>();
		
    	for (Product i : repository.findAll()) {
    		products.add(ProductConverter.toDTO(i));
    	}
    	return products;
	}

	@Override
	public ProductDTO readById(long id) {
		Product product = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
    	return ProductConverter.toDTO(product);
	}

	@Override
	public ProductDTO create(ProductDTO productDTO) {
		repository.save(ProductConverter.toEntity(productDTO));
		return productDTO;
	}	
	
	@Override
	public ProductDTO update(long id, ProductDTO productDTO) {

		Optional<Product> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		Product product = ProductConverter.toEntity(productDTO);
		product.setForeignKey(currentEntityWrapper.get());
		this.repository.save(product);
		return productDTO;
	}
	
	@Override
	public void delete(long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		this.repository.deleteById(id);
	}

}
