package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.CategoryDTO;

public interface ICategoryService {

	List<CategoryDTO> readAll();

	CategoryDTO readById(long id);

	CategoryDTO create(CategoryDTO categoryDTO);

	CategoryDTO update(long id, CategoryDTO categoryDTO);

	void delete(long id);
	
}
