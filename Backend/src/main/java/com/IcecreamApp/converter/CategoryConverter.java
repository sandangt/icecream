package com.IcecreamApp.converter;

import org.springframework.stereotype.Component;

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.entity.Category;

@Component
public class CategoryConverter implements IConverter<CategoryDTO, Category>{

	@Override
	public CategoryDTO toDTO(CategoryDTO dto, Category entity) {
		return null;
	}

	@Override
	public Category toEntity(CategoryDTO dto, Category entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
