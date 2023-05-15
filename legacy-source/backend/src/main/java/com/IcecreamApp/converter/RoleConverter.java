package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.RoleDTO;
import com.IcecreamApp.entity.Role;

public class RoleConverter {
	public static RoleDTO toDTO(Role entity) {
		if (entity == null)
			return null;
		return new RoleDTO(entity.getId(), entity.getModifiedDate(), entity.getName());
	}
	
	public static Role toEntity(RoleDTO dto) {
		if (dto == null)
			return null;
		return new Role(dto.getId(), dto.getName());
	}
}
