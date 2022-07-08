package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.CategoryDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.DTO.ProductDTO;
import com.icecream.product.entity.Category;

public interface ICategoryService {

	List<CategoryDTO> readAll();

	Optional<CategoryDTO> readById(long id);

	Category create(CategoryDTO categoryDTO);

	Optional<Category> update(long id, CategoryDTO categoryDTO);

	boolean delete(long id);
	
	List<CategoryDTO> searchCategoriesByName(String categoryname);
	
	List<CategoryDTO> readAllNameAndId();
	
	List<ProductDTO> readAllProductsByCategory(long id);
	
	PageDTO<ProductDTO> readAllProductsByCategoryWithPage(long id, int pageNumber, int pageSize);
}
