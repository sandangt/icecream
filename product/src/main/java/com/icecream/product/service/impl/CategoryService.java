package com.icecream.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.icecream.product.converter.ProductConverter;
import com.icecream.product.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.icecream.product.DTO.CategoryDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.DTO.ProductDTO;
import com.icecream.product.converter.CategoryConverter;
import com.icecream.product.entity.Category;
import com.icecream.product.entity.Product;
import com.icecream.product.repository.CategoryRepository;
import com.icecream.product.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	private String entityName = "Category"; 
	
	@Override
	public List<CategoryDTO> readAll() {
    	
    	return categoryRepository.findAll().stream().map(CategoryConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<CategoryDTO> readById(long id) {
		Optional<Category> currentEntityWrapper = categoryRepository.findById(id);
		if (currentEntityWrapper.isPresent())
			return Optional.ofNullable(CategoryConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public Category create(CategoryDTO categoryDTO) {
		return categoryRepository.save(CategoryConverter.toEntity(categoryDTO));
	}

	@Override
	public Optional<Category> update(long id, CategoryDTO categoryDTO) {
		Optional<Category> currentEntityWrapper = categoryRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			Category category = CategoryConverter.toEntity(categoryDTO);
			category.setForeignKey(currentEntityWrapper.get());
			return Optional.ofNullable(this.categoryRepository.save(category));
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<Category> currentEntityWrapper = categoryRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.categoryRepository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}

	@Override
	public List<CategoryDTO> searchCategoriesByName(String categoryname) {
		return categoryRepository.searchCategoriesByName(categoryname).stream().map(CategoryConverter::toDTO).collect(Collectors.toList());		
	}

	@Override
	public List<CategoryDTO> readAllNameAndId() {
		List<CategoryDTO> result = new ArrayList<>();
		for (Category i : categoryRepository.findAll()) {
			result.add(new CategoryDTO(i.getId(), i.getModifiedDate(), i.getName(), null));
		}
		return result;
	}

	@Override
	public List<ProductDTO> readAllProductsByCategory(long id) {
		Optional<Category> currentEntityWrapper = categoryRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return productRepository.getProductsByCategory(id).stream().map(ProductConverter::toDTO).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	@Override
	public PageDTO<ProductDTO> readAllProductsByCategoryWithPage(long id, int pageNumber, int pageSize) {
		Optional<Category> currentEntityWrapper = categoryRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			int totalItem = currentEntityWrapper.get().getProducts().size();
			Page<Product> pages = productRepository.getPaginatedProductsByCategory(id, PageRequest.of(--pageNumber, pageSize));
			return new PageDTO<>((long) totalItem, pages.getContent().stream().map(ProductConverter::toDTO).collect(Collectors.toList()));
		}
		return new PageDTO<>(0L, new ArrayList<>());
	}
}
