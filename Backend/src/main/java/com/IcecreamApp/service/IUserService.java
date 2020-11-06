package com.IcecreamApp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.IcecreamApp.DTO.PasswordDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.systemConstant.EStatus;

public interface IUserService {

	List<UserDTO> readAll();

	Optional<UserDTO> readById(long id);

	User create(UserDTO userDTO);

	Optional<User> update(long id, UserDTO userDTO);

	boolean delete(long id);
	
	Map.Entry<Long, List<UserDTO>> readByPage(int pageNumber, int pageSize);
	
	boolean changePassword(long id, PasswordDTO passwords);
	
	Optional<UserDetail> updateProfile(long id, UserDetailDTO newProfile);
	
	boolean changeUserStatus(long id, EStatus newStatus);
	
	List<UserDTO> readAllByUsername(String username);
	
}
