package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.RoleDTO;
import com.IcecreamApp.entity.Role;

public class RoleConverter {
	public static RoleDTO toDTO(Role entity) {
		return new RoleDTO(entity.getId(), entity.getModifiedDate(), entity.getName());
	}
	
	public static Role toEntity(RoleDTO dto) {
		return new Role(dto.getName());
	}
}
