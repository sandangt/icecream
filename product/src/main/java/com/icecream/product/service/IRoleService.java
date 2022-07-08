package com.icecream.product.service;

import java.util.List;

import com.icecream.product.DTO.RoleDTO;

public interface IRoleService {

	RoleDTO create(RoleDTO roleDTO);

	List<RoleDTO> readAll();

	RoleDTO readById(long id);

	RoleDTO update(long id, RoleDTO roleDTO);

	void delete(long id);

}
