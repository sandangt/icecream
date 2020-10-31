package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.RoleDTO;

public interface IRoleService {

	RoleDTO create(RoleDTO roleDTO);

	List<RoleDTO> readAll();

	RoleDTO readById(long id);

	RoleDTO update(long id, RoleDTO roleDTO);

	void delete(long id);

}