package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.entity.Feedback;

public class FeedbackConverter {
	public static FeedbackDTO toDTO(Feedback entity) {
		
		return new FeedbackDTO(entity.getId(), entity.getModifiedDate(), entity.getTitle(), entity.getContent(), UserConverter.toDTO(entity.getUser()));
	}
	
	public static Feedback toEntity(FeedbackDTO dto) {
		return new Feedback(dto.getTitle(), dto.getContent(), UserConverter.toEntity(dto.getUser()), null);
	}
}