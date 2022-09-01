package com.icecream.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.icecream.product.converter.FeedbackConverter;
import com.icecream.product.converter.ProductConverter;
import com.icecream.product.repository.FeedbackRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.icecream.product.DTO.FeedbackDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.DTO.ProductDTO;
import com.icecream.product.entity.Product;
import com.icecream.product.repository.ProductRepository;
import com.icecream.product.service.IProductService;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private FeedbackRepository feedbackRepository;
	private String entityName = "Product";
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Override
	public List<ProductDTO> readAll() {    	
    	return productRepository.findAll().stream().map(ProductConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> readById(long id) {
		Optional<Product> currentEntityWrapper = productRepository.findById(id);
		if (currentEntityWrapper.isPresent()) 
			return Optional.ofNullable(ProductConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public Product create(ProductDTO productDTO) {
		return productRepository.save(ProductConverter.toEntity(productDTO));
	}	
	
	@Override
	public Optional<Product> update(long id, ProductDTO productDTO) {

		Optional<Product> currentEntityWrapper = productRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			Product product = ProductConverter.toEntity(productDTO);
			product.setForeignKey(currentEntityWrapper.get());
			return Optional.ofNullable(productRepository.save(product));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}
	
	@Override
	public boolean delete(long id) {
		Optional<Product> currentEntityWrapper = productRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.productRepository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}

	@Override
	public PageDTO<ProductDTO> readByPage(int pageNumber, int pageSize) {
		Page<Product> pages = productRepository.findAll(PageRequest.of(--pageNumber, pageSize));
		Long totalEntities = productRepository.count();
		return new PageDTO<>(totalEntities, pages.getContent().stream().map(ProductConverter::toDTO).collect(Collectors.toList()));
	}

	@Override
	public List<ProductDTO> searchProductsByName(String productname) {
		return productRepository.searchProductsByName(productname).stream().map(ProductConverter::toDTO).collect(Collectors.toList());
		
	}

	@Override
	public List<FeedbackDTO> readFeedbacksByProduct(long id) {
		Optional<Product> currentEntityWrapper = productRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return feedbackRepository.findFeedbacksByProduct(id).stream().map(FeedbackConverter::toDTO).collect(Collectors.toList());
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return new ArrayList<>();
	}
}
