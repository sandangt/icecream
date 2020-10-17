package com.IcecreamApp.service.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.service.IUserService;

@Service
public class UserService extends GeneralService<User, UserRepository> implements IUserService {

	@Override
	public ResponseEntity<User> update(long id, User user) {

		Optional<User> currentUserWrapper = this.repository.findById(id);

	    if (!currentUserWrapper.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    User currentUser = currentUserWrapper.get();
	    BeanUtils.copyProperties(user, currentUser);
	    this.repository.save(currentUser);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public ResponseEntity<User> delete(long id) {
		
    	Optional<User> currentUserWrapper = repository.findById(id);
        if (!currentUserWrapper.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User currentUser = currentUserWrapper.get();
        currentUser.getRoles().remove(currentUser);
        repository.delete(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
	}
}
