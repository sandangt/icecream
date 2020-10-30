package com.IcecreamApp.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.repository.UserRepository;

public interface IUserService extends IGeneralService<User, UserRepository> {
	public UserDTO createDTO(@RequestBody UserDTO userDTO);
}
