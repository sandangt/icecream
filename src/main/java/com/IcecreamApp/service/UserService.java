package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.repository.UserRepository;

@Service
public class UserService extends GeneralService<User, UserRepository> {
	
	public UserService(UserRepository repository) {
		super(repository);
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
