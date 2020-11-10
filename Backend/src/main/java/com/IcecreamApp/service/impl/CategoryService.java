package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.converter.CategoryConverter;
import com.IcecreamApp.converter.ProductConverter;
import com.IcecreamApp.entity.Category;
import com.IcecreamApp.entity.Product;
import com.IcecreamApp.repository.CategoryRepository;
import com.IcecreamApp.repository.ProductRepository;
import com.IcecreamApp.service.ICategoryService;
import com.google.common.collect.Maps;

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
	public Map.Entry<Long, List<ProductDTO>> readAllProductsByCategoryWithPage(long id, int pageNumber, int pageSize) {
		Optional<Category> currentEntityWrapper = categoryRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			int totalItem = currentEntityWrapper.get().getProducts().size();
			Page<Product> pages = productRepository.getPaginatedProductsByCategory(id, PageRequest.of(pageNumber, pageSize));
			return Maps.immutableEntry((long) totalItem, pages.getContent().stream().map(ProductConverter::toDTO).collect(Collectors.toList()));
		}
		return Maps.immutableEntry(0L, new ArrayList<>());
	}
}