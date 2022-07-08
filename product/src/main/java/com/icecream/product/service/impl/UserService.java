package com.icecream.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.icecream.product.converter.OrderConverter;
import com.icecream.product.converter.RoleConverter;
import com.icecream.product.converter.UserConverter;
import com.icecream.product.converter.UserDetailConverter;
import com.icecream.product.repository.OrderRepository;
import com.icecream.product.repository.UserDetailRepository;
import com.icecream.product.repository.UserRepository;
import com.icecream.product.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.icecream.product.DTO.OrderDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.DTO.PasswordDTO;
import com.icecream.product.DTO.RolesAndStatusDTO;
import com.icecream.product.DTO.UserDTO;
import com.icecream.product.DTO.UserDetailDTO;
import com.icecream.product.entity.Role;
import com.icecream.product.entity.User;
import com.icecream.product.entity.UserDetail;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailRepository userDetailRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	private String entityName = "User";
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public List<UserDTO> readAll() {
    	return userRepository.findAll().stream().map(UserConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<UserDTO> readById(long id) {
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent())
			return Optional.ofNullable(UserConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public User create(UserDTO userDTO) {
		return userRepository.save(UserConverter.toEntity(userDTO));
	}	
	
	@Override
	public Optional<User> update(long id, UserDTO userDTO) {
		
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			User user = UserConverter.toEntity(userDTO);
			user.setForeignKey(currentEntityWrapper.get());
			return Optional.ofNullable(this.userRepository.save(user));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public boolean delete(long id) {
        
        Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
	        User currentUser = currentEntityWrapper.get();
	        currentEntityWrapper.get().getRoles().remove(currentUser);
			userRepository.deleteById(id);
			return true;
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}

	@Override
	public boolean changePassword(long id, PasswordDTO passwords) {
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			if (encoder.matches(passwords.getOldPassword(), currentEntityWrapper.get().getPassword())) {
					User user = currentEntityWrapper.get();
					user.setPassword(encoder.encode(passwords.getNewPassword()));
					userRepository.save(user);
					return true;
			}
			logger.error(String.format("%s id %d not found", entityName, id));
		}
		return false;
	}

	@Override
	public Optional<UserDetail> updateProfile(long id, UserDetailDTO newProfile) {
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			newProfile.setId(id);
			return Optional.ofNullable(userDetailRepository.save(UserDetailConverter.toEntity(newProfile)));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public PageDTO<UserDTO> readByPage(int pageNumber, int pageSize) {
		Page<User> pages = userRepository.findAll(PageRequest.of(--pageNumber, pageSize));
		Long totalEntities = userRepository.count();
		return new PageDTO<>(totalEntities, pages.getContent().stream().map(UserConverter::toDTO).collect(Collectors.toList()));
	}

	@Override
	public List<UserDTO> searchUsersByUsername(String username) {
		return userRepository.searchUsersByUsername(username).stream().map(UserConverter::toDTO).collect(Collectors.toList());
	}
	
	@Override
	public Optional<User> updateRolesAndStatus(long id, RolesAndStatusDTO rolesNstatus) {
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			User user = currentEntityWrapper.get();
			user.setStatus(rolesNstatus.getStatus());
			Set<Role> roles = rolesNstatus.getRoles().stream().map(RoleConverter::toEntity).collect(Collectors.toSet());
			user.setRoles(roles);
			return Optional.ofNullable(userRepository.save(user));
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public Optional<User> updateUsernameAndEmail(long id, UserDTO userDTO) {
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			User user = currentEntityWrapper.get();
			user.setUserName(userDTO.getUsername());
			user.setEmail(userDTO.getEmail());
			return Optional.ofNullable(userRepository.save(user));
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
		
	}

	@Override
	public List<OrderDTO> readAllOrdersByUser(long id) {
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return orderRepository.findByUserId(id).stream().map(OrderConverter::toDTO).collect(Collectors.toList());
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return new ArrayList<>();
	}
}
