package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.DTO.PageDTO;
import com.IcecreamApp.DTO.PasswordDTO;
import com.IcecreamApp.DTO.RolesAndStatusDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.entity.UserDetail;

public interface IUserService {

	List<UserDTO> readAll();

	Optional<UserDTO> readById(long id);

	User create(UserDTO userDTO);

	Optional<User> update(long id, UserDTO userDTO);

	boolean delete(long id);
	
	PageDTO<UserDTO> readByPage(int pageNumber, int pageSize);
	
	boolean changePassword(long id, PasswordDTO passwords);
	
	Optional<UserDetail> updateProfile(long id, UserDetailDTO newProfile);
	
	List<UserDTO> searchUsersByUsername(String username);

	Optional<User> updateRolesAndStatus(long id, RolesAndStatusDTO rolesNstatus);
	
	Optional<User> updateUsernameAndEmail(long id, UserDTO userDTO);
	
}
