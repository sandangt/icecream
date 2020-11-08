package com.IcecreamApp.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.PasswordDTO;
import com.IcecreamApp.DTO.UserDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.service.IUserService;
import com.IcecreamApp.systemConstant.EStatus;

@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private IUserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUser() {           
		return ResponseEntity.ok().body(userService.readAll());     
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") long id) {
    	Optional<UserDTO> currentEntityWrapper = userService.readById(id);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
    	}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createUser(@RequestBody UserDTO userDTO) {
    	this.userService.create(userDTO);
    	return ResponseEntity.ok().body(new MessageResponseDTO("Created new User successfully"));
    }
    

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
    	Optional<User> currentEntityWrapper = userService.update(id, userDTO);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated user successfully"));
    	}
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")	
//    @PreAuthorize("hasAuthority('USER_WRITE')")
    public ResponseEntity<MessageResponseDTO> deleteUser(@PathVariable("id") long id) {
    	if (userService.delete(id)) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("User has been deleted successfully!"));
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }
    
    @PutMapping(value="/{id}/password")
    public ResponseEntity<MessageResponseDTO> changePassword(@PathVariable("id") long id, @RequestBody PasswordDTO passwords) {
    	if (this.userService.changePassword(id, passwords)) {
    		return new ResponseEntity<>(new MessageResponseDTO("Password updated successfully!"), HttpStatus.OK);
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Incorrect password!"), HttpStatus.NOT_ACCEPTABLE);
    }
	
    @GetMapping(params={"page","offset"})
    public ResponseEntity<Map.Entry<Long,List<UserDTO>>> getUsersByPage(@RequestParam("page") int page, @RequestParam("offset") int offset) {
    	/**
    	 * return key - value pair
    	 * key is total tuples in the table in database
    	 * value is list of tuples per page
    	 */
    	return ResponseEntity.ok().body(userService.readByPage(page, offset));
    }

    @PutMapping(value="/{id}/user-profile")
    public ResponseEntity<MessageResponseDTO> updateProfile(@PathVariable("id") long id, @RequestBody UserDetailDTO newProfile) {
    	Optional<UserDetail> currentEntityWrapper = this.userService.updateProfile(id, newProfile); 
    	if (currentEntityWrapper.isPresent()) {
    		return new ResponseEntity<>(new MessageResponseDTO("Update profile successfully!"), HttpStatus.OK);
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    	
    }
    @PutMapping(value="/{id}/user-status")
    public ResponseEntity<MessageResponseDTO> changeStatus(@PathVariable("id") long id, @RequestBody EStatus newStatus) {
    	if (this.userService.changeUserStatus(id, newStatus)) {
    		return new ResponseEntity<>(new MessageResponseDTO("Update status successfully!"), HttpStatus.OK);
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Update status failed!"), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @GetMapping(params="search")
    public ResponseEntity<List<UserDTO>> searchUsersByUsername(@RequestParam("search") String username) {
    	return ResponseEntity.ok().body(userService.searchUsersByUsername(username));
    }
        
}