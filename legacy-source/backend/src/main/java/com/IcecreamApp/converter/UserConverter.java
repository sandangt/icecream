package com.IcecreamApp.converter;

import java.util.HashSet;
import java.util.Set;

import com.IcecreamApp.DTO.RoleDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.entity.Role;
import com.IcecreamApp.entity.User;

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