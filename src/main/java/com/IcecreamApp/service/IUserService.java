package com.IcecreamApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.IcecreamApp.entity.User;

public interface IUserService {
	
	ResponseEntity<List<User>> findAll();
	
 	ResponseEntity<User> findById(long id);   
 	
 	ResponseEntity<User> create(User user);
 	
 	ResponseEntity<User> update(long id, User user);
 	
 	ResponseEntity<User> delete(long id);
	
}
