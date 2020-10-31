package com.IcecreamApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.systemConstant.EStatus;

public interface IUserService {

	UserDTO create(UserDTO userDTO);

	List<UserDTO> readAll();

	UserDTO readById(long id);

	UserDTO update(long id, UserDTO userDTO);

	void delete(long id);
	
	ResponseEntity<MessageResponseDTO> changePassword(long id, String[] passwords);
	
	ResponseEntity<MessageResponseDTO> updateProfile(long id, UserDetailDTO newProfile);
	
	ResponseEntity<MessageResponseDTO> changeUserStatus(long id, EStatus newStatus);
}
