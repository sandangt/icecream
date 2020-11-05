package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.PasswordDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.converter.UserConverter;
import com.IcecreamApp.converter.UserDetailConverter;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.UserDetailRepository;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.service.IUserService;
import com.IcecreamApp.systemConstant.EStatus;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailRepository userDetailRepository;
	@Autowired
	private PasswordEncoder encoder;
	
	private String entityName = "User";
	
	Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	@Override
	public List<UserDTO> readAll() {
		
		List<UserDTO> users = new ArrayList<>();
		
    	for (User i : userRepository.findAll()) {
    		users.add(UserConverter.toDTO(i));
    	}
    	return users;
	}

	@Override
	public UserDTO readById(long id) {
    	User user = userRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
    	return UserConverter.toDTO(user);
	}

	@Override
	public UserDTO create(UserDTO userDTO) {
		userRepository.save(UserConverter.toEntity(userDTO));
		return userDTO;
	}	
	
	@Override
	public UserDTO update(long id, UserDTO userDTO) {

//		Optional<Role> currentEntityWrapper = this.repository.findById(id);
//
//	    if (!currentEntityWrapper.isPresent()) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    entity.setForeignKey(currentEntityWrapper.get());
//	    System.out.println(currentEntityWrapper.get().getUsers());
//	    this.repository.saveAndFlush(entity);
//		return new ResponseEntity<>(entity, HttpStatus.OK);
		
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		User user = UserConverter.toEntity(userDTO);
		user.setForeignKey(currentEntityWrapper.get());
		this.userRepository.save(user);
		return userDTO;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void delete(long id) {
//    	Optional<User> currentUserWrapper = repository.findById(id);
//        if (!currentUserWrapper.isPresent())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        User currentUser = currentUserWrapper.get();
//    	currentUser.getRoles().remove(currentUser);
//        repository.delete(currentUser);
//        return new ResponseEntity<>(currentUser, HttpStatus.OK);
        
        Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
        User currentUser = currentEntityWrapper.get();
        currentEntityWrapper.get().getRoles().remove(currentUser);
        userRepository.delete(currentUser);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> changePassword(long id, PasswordDTO passwords) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		if (!encoder.matches(passwords.getOldPassword(), user.getPassword())) 
			return new ResponseEntity<>(new MessageResponseDTO("Incorrect password!"), HttpStatus.NOT_ACCEPTABLE);
		user.setPassword(encoder.encode(passwords.getNewPassword()));
		userRepository.save(user);
		return new ResponseEntity<>(new MessageResponseDTO("Password updated successfully!"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> updateProfile(long id, UserDetailDTO newProfile) {
		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		newProfile.setId(id);
		userDetailRepository.save(UserDetailConverter.toEntity(newProfile));
		return new ResponseEntity<>(new MessageResponseDTO("Update profile successfully!"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> changeUserStatus(long id, EStatus newStatus) {
		User user = userRepository.findById(id)
			.orElseThrow( () -> new ResourceNotFoundException(this.entityName, id));
		user.setStatus(newStatus);
		userRepository.save(user);
		return new ResponseEntity<>(new MessageResponseDTO("Update status successfully!"), HttpStatus.OK);
	}

//	@Override
//	public String changePassword(long id, String[] passwords) {
//		Optional<User> currentUserWrapper = repository.findById(id);
//		if (!currentUserWrapper.isPresent()) {
//			throw new ResourceNotFoundException(this.entityName, id);
//	    }
//		User currentUser = currentUserWrapper.get();
//		String oldPassword = currentUserWrapper.get().getPassword();
//		String inputOldpassword = encoder.encode(passwords[0].getBytes());
//		if (!oldPassword.equals(encoderpasswords[0])) {
//			return "Unsuccessful";
//		}
//		currentUser.set		
//		
//	}
}
