package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.entity.UserDetail;

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
