package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.entity.FAQ;

public class FAQConverter {
	
	public static FAQDTO toDTO(FAQ entity) {
		if (entity == null) 
			return null;
		return new FAQDTO(entity.getId(), entity.getModifiedDate(), entity.getQuestion(), entity.getAnswer());
	}
	
	public static FAQ toEntity(FAQDTO dto) {
		if (dto == null) 
			return null;
		return new FAQ(dto.getId(), dto.getQuestion(), dto.getAnswer());
	}
}
