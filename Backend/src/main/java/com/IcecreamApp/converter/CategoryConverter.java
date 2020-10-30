package com.IcecreamApp.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.IcecreamApp.DTO.CategoryDTO;
import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.entity.Category;
import com.IcecreamApp.entity.Product;

public class CategoryConverter {

	public static CategoryDTO toDTO(Category entity) {
		List<ProductDTO> products;
		for (Product i : entity.getProducts()) {
			products.add(ProductConverter.toDTO(i));
		}
		return new CategoryDTO(entity.getId(), entity.getModifiedDate(), entity.getName(), products);
		
	}

	public static Category toEntity(CategoryDTO dto, Category entity) {
		return new Category(dto.getName());
	}
}
