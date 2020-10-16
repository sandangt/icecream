package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.IcecreamApp.entity.User;

public interface IUserService {
	
	ResponseEntity<List<User>> findAll();
	
 	Optional<User> findById(long id);   
 	
 	void save(User user);    
 	
 	void delete(User user);
	
}
