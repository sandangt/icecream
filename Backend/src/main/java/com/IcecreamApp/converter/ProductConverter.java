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

		List<FeedbackDTO> feedbacks = new ArrayList<>();
		for (Feedback i : entity.getFeedbacks()) {
			feedbacks.add(FeedbackConverter.toDTO(i));
		}
	    
		return new ProductDTO(entity.getId(), 
				entity.getModifiedDate(), 
				entity.getName(), 
				entity.getDescription(), 
				entity.getImage(), 
				entity.getPrice(), 
				entity.getStatus(), 
				feedbacks, 
				entity.getCategory().getId());
		
	}

	public static Product toEntity(ProductDTO dto) {
		if (dto == null)
			return null;
		Category category = new Category();
		category.setId(dto.getCategoryId());
		return new Product(dto.getName(), 
				dto.getDescription(),
				dto.getImage(),
				dto.getPrice(),
				dto.getStatus(),
				category);
	}
	
}
