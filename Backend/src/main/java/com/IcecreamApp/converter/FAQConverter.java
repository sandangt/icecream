package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.entity.FAQ;

public class FAQConverter {
	
	public static FAQDTO toDTO(FAQ entity) {
		return new FAQDTO(entity.getId(), entity.getModifiedDate(), entity.getQuestion(), entity.getAnswer());
	}
	
	public static FAQ toEntity(FAQDTO dto) {
		return new FAQ(dto.getQuestion(), dto.getAnswer());
	}
}
