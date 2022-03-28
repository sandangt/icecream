package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.OrderDTO;
import com.IcecreamApp.DTO.PageDTO;
import com.IcecreamApp.DTO.PasswordDTO;
import com.IcecreamApp.DTO.RolesAndStatusDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.converter.OrderConverter;
import com.IcecreamApp.converter.RoleConverter;
import com.IcecreamApp.converter.UserConverter;
import com.IcecreamApp.converter.UserDetailConverter;
import com.IcecreamApp.entity.Role;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.repository.OrderRepository;
import com.IcecreamApp.repository.UserDetailRepository;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.service.IUserService;

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
