package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.converter.UserConverter;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.service.IUserService;

@Service
public class UserService extends GeneralService<User, UserRepository> implements IUserService {
	
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
        
        Optional<User> currentUserWrapper = repository.findById(id);
		if (!currentUserWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
        User currentUser = currentUserWrapper.get();
        currentUserWrapper.get().getRoles().remove(currentUser);
        repository.delete(currentUser);
	}

	@Override
	public User update(long id, User entity) {

//		Optional<User> currentEntityWrapper = this.repository.findById(id);
//
//	    if (!currentEntityWrapper.isPresent()) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    entity.setForeignKey(currentEntityWrapper.get());
//	    this.repository.saveAndFlush(entity);
//		return new ResponseEntity<>(entity, HttpStatus.OK);
		
		Optional<User> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		entity.setForeignKey(currentEntityWrapper.get());
		return this.repository.save(entity);
	}

	@Override
	public UserDTO createDTO(UserDTO userDTO) {
		User user = UserConverter.toEntity(userDTO);
		System.out.println("password: " + user.getPassword());
		System.out.println("username: " + user.getUserName());
		this.repository.save(user);
		return userDTO;
	}

	@Override
	public List<UserDTO> readDTO() {
		List<UserDTO> listUser = new ArrayList<>();
		for (User i : repository.findAll()) {
			listUser.add(UserConverter.toDTO(i));
		}
		return listUser;
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
