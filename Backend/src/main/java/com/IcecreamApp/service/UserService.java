package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.UserRepository;

@Service
public class UserService extends GeneralService<User, UserRepository> {
	
	public UserService(UserRepository repository) {
		super(repository);
		entityName = "user";
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
}
