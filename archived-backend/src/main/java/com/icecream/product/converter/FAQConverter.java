package com.icecream.product.converter;

import com.icecream.product.DTO.FAQDTO;
import com.icecream.product.entity.FAQ;

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
