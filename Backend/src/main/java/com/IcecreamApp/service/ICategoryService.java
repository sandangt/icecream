package com.IcecreamApp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.entity.Category;

public interface ICategoryService {

	List<CategoryDTO> readAll();

	Optional<CategoryDTO> readById(long id);

	Category create(CategoryDTO categoryDTO);

	Optional<Category> update(long id, CategoryDTO categoryDTO);

	boolean delete(long id);
	
	List<CategoryDTO> searchCategoriesByName(String categoryname);
	
	List<CategoryDTO> readAllNameAndId();
	
	List<ProductDTO> readAllProductsByCategory(long id);
	
	Map.Entry<Long, List<ProductDTO>> readAllProductsByCategoryWithPage(long id, int pageNumber, int pageSize);
}
