package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getAllUser() {        
    	return userService.readAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
    	return userService.readById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	return this.userService.create(user);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    	return this.userService.update(id, user);
    }

    @DeleteMapping(value = "/{id}")	
    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
    	return this.userService.delete(id);
    }
}