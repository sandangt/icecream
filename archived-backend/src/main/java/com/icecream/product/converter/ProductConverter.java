package com.icecream.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.icecream.product.DTO.FeedbackDTO;
import com.icecream.product.DTO.ProductDTO;
import com.icecream.product.entity.Category;
import com.icecream.product.entity.Feedback;
import com.icecream.product.entity.Product;

public class ProductConverter {

	public static ProductDTO toDTO(Product entity) {
		if (entity == null)
			return null;
		
		List<FeedbackDTO> feedbacks = new ArrayList<>();
		if (entity.getFeedbacks() != null) {
			for (Feedback i : entity.getFeedbacks()) {
				feedbacks.add(FeedbackConverter.toDTO(i));
			}
		}
		
		String categoryName = entity.getCategory() != null ? entity.getCategory().getName() : null;
	    
		return new ProductDTO(entity.getId(), 
				entity.getModifiedDate(), 
				entity.getName(), 
				entity.getDescription(), 
				entity.getImage(), 
				entity.getPrice(), 
				entity.getStatus(), 
				feedbacks, 
				categoryName);
	}

	public static Product toEntity(ProductDTO dto) {
		if (dto == null)
			return null;
		Category category = new Category();
		category.setId(dto.getCategoryId());
		return new Product(dto.getId(),
				dto.getName(), 
				dto.getDescription(),
				dto.getImage(),
				dto.getPrice(),
				dto.getStatus(),
				category);
	}
	
}
