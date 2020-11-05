package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.PasswordDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.service.IUserService;
import com.IcecreamApp.systemConstant.EStatus;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private IUserService userService;

    @GetMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDTO> getAllUser() {        
    	return userService.readAll();
    }

    @GetMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('USER_READ')")
    public UserDTO getUserById(@PathVariable("id") long id) {
    	return userService.readById(id);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('USER_WRITE')")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
    	return this.userService.create(userDTO);
    }
    

    @PutMapping(value = "/{id}")
//    @PreAuthorize("hasAuthority('USER_WRITE')")
    public UserDTO updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
    	return this.userService.update(id, userDTO);
    }

    @DeleteMapping(value = "/{id}")	
//    @PreAuthorize("hasAuthority('USER_WRITE')")
    public void deleteUser(@PathVariable("id") long id) {
    	this.userService.delete(id);
    }
    
    @PutMapping(value="/{id}/password")
    public ResponseEntity<MessageResponseDTO> changePassword(@PathVariable("id") long id, @RequestBody PasswordDTO passwords) {
    	return this.userService.changePassword(id, passwords);
    }

    @PutMapping(value="/{id}/user-profile")
    public ResponseEntity<MessageResponseDTO> updateProfile(@PathVariable("id") long id, @RequestBody UserDetailDTO newProfile) {
    	return this.userService.updateProfile(id, newProfile);
    }
    
    @PutMapping(value="/{id}/user-status")
    public ResponseEntity<MessageResponseDTO> changeStatus(@PathVariable("id") long id, @RequestBody EStatus newStatus) {
    	return this.userService.changeUserStatus(id, newStatus);
    }
        
}