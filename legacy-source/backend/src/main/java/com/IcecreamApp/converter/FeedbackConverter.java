package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.entity.Product;

public class FeedbackConverter {
	public static FeedbackDTO toDTO(Feedback entity) {
		
		if (entity == null)
			return null;
				
		return new FeedbackDTO(entity.getId(), 
				entity.getModifiedDate(), 
				entity.getTitle(), 
				entity.getContent(), 
				UserConverter.toDTO(entity.getUser()), 
				entity.getProduct().getName(),
				entity.getProduct().getId());
	}
	
	public static Feedback toEntity(FeedbackDTO dto) {
		if (dto == null)
			return null;
		Product product = new Product();
		product.setId(dto.getProductId());
		return new Feedback(dto.getId(),
				dto.getTitle(), 
				dto.getContent(), 
				UserConverter.toEntity(dto.getUser()),
				product);
	}
}