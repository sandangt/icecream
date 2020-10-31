package com.IcecreamApp.converter;

import java.util.ArrayList;
import java.util.List;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.entity.Category;
import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.entity.Product;

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
		System.out.println(entity.getCategory().getId());
		System.out.println(entity.getCategory().getName());
	    
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
