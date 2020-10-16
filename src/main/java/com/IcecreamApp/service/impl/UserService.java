package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public ResponseEntity<List<User>> findAll() {
		
    	List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> findById(long id) {
    	Optional<User> user = this.userRepository.findById(id);

        if (!user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> create(User user) {
		userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<User> update(long id, User user) {

		Optional<User> currentUser = this.userRepository.findById(id);

	    if (!currentUser.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    this.userRepository.delete(user);
	    this.userRepository.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<User> delete(long id) {
		
    	Optional<User> currentUser = userRepository.findById(id);
        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRepository.delete(currentUser.get());
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
	}
}
