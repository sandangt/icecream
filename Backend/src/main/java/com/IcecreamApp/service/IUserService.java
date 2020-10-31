package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.UserDTO;

public interface IUserService {

	UserDTO create(UserDTO userDTO);

	List<UserDTO> readAll();

	UserDTO readById(long id);

	UserDTO update(long id, UserDTO userDTO);

	void delete(long id);

}
