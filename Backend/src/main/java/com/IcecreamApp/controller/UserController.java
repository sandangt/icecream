package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.service.IUserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private IUserService userService;

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUser() {        
    	return userService.readAll();
    }

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('USER_READ')")
    public User getUserById(@PathVariable("id") Long id) {
    	return userService.readById(id);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('USER_WRITE')")
    public User createUser(@RequestBody User user) {
    	return this.userService.create(user);
    }
    

    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('USER_WRITE')")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
    	return this.userService.update(id, user);
    }

    @DeleteMapping(value = "/{id}")	
//    @PreAuthorize("hasAuthority('USER_WRITE')")
    public void deleteUser(@PathVariable("id") Long id) {
    	this.userService.delete(id);
    }
    
    @PostMapping(value="/test")
    public UserDTO createUser(@RequestBody UserDTO user) {
    	return this.userService.createDTO(user);
    }
    
}