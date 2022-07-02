package com.icecream.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.icecream.product.DTO.CategoryDTO;
import com.icecream.product.DTO.ProductDTO;
import com.icecream.product.entity.Category;
import com.icecream.product.entity.Product;

public class CategoryConverter {

	public static CategoryDTO toDTO(Category entity) {

		if (entity == null)
			return null;
		List<ProductDTO> products = new ArrayList<>();
		if (entity.getProducts() != null) {
			for (Product i : entity.getProducts()) {
				products.add(ProductConverter.toDTO(i));
			}
		}
		return new CategoryDTO(entity.getId(), entity.getModifiedDate(), entity.getName(), products);
		
	}

	public static Category toEntity(CategoryDTO dto) {
		if (dto == null)
			return null;
		return new Category(dto.getId(), dto.getName());
	}
}
