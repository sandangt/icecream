package com.icecream.product.converter;

import com.icecream.product.DTO.RoleDTO;
import com.icecream.product.entity.Role;

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
