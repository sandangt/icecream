package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.OrderDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.DTO.PasswordDTO;
import com.icecream.product.DTO.RolesAndStatusDTO;
import com.icecream.product.DTO.UserDTO;
import com.icecream.product.DTO.UserDetailDTO;
import com.icecream.product.entity.User;
import com.icecream.product.entity.UserDetail;

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
	
	List<OrderDTO> readAllOrdersByUser(long id);
	
}
