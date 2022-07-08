package com.icecream.product.converter;

import java.util.HashSet;
import java.util.Set;

import com.icecream.product.DTO.RoleDTO;
import com.icecream.product.DTO.UserDTO;
import com.icecream.product.entity.Role;
import com.icecream.product.entity.User;

public class UserConverter {
	public static UserDTO toDTO(User entity) {
		if (entity == null)
			return null;
		
		Set<RoleDTO> roles = new HashSet<>();
		if (entity.getRoles() != null)
		{
			for (Role i : entity.getRoles()) {
				roles.add(RoleConverter.toDTO(i));
			}
		}
		
		return new UserDTO(entity.getId(), 
				entity.getModifiedDate(), 
				entity.getUserName(), 
				entity.getEmail(), 
				entity.getPassword(), 
				entity.getStatus(), 
				UserDetailConverter.toDTO(entity.getUserDetail()), roles);
	}
	
	public static User toEntity(UserDTO dto) {

		if (dto == null)
			return null;
		Set<Role> roles = new HashSet<>();
		if (dto.getRoles() != null) {
			for (RoleDTO i : dto.getRoles()) {
				roles.add(RoleConverter.toEntity(i));
			}
		}
		return new User(dto.getId(), dto.getUsername(), dto.getEmail(), dto.getPassword(), dto.getStatus(), roles);
	}
}
