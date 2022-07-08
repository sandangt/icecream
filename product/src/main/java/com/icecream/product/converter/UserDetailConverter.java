package com.icecream.product.converter;

import com.icecream.product.DTO.UserDetailDTO;
import com.icecream.product.entity.User;
import com.icecream.product.entity.UserDetail;

public class UserDetailConverter {
	public static UserDetailDTO toDTO(UserDetail entity) {
		if (entity == null)
			return null;
		return new UserDetailDTO(entity.getId(), 
				entity.getModifiedDate(), 
				entity.getFirstname(), 
				entity.getLastname(), 
				entity.getAddress(), 
				entity.getGender(),
				entity.getBirthday(), 
				entity.getAvatar()); 
	}
	
	public static UserDetail toEntity(UserDetailDTO dto) {
		if (dto == null) 
			return null;
		User user = new User();
		user.setId(dto.getId());
		return new UserDetail(dto.getId(),
				dto.getFirstname(), 
				dto.getLastname(), 
				dto.getAddress(), 
				dto.getGender(),
				dto.getBirthday(), 
				dto.getAvatar(),
				user); 
	}
}
