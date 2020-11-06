package com.IcecreamApp.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.service.IUserDetailService;

@CrossOrigin
@RestController
@RequestMapping("user-details")
public class UserDetailController {
	
	@Autowired
	private IUserDetailService userDetailService;

    @GetMapping
    public ResponseEntity<List<UserDetailDTO>> getAllUserDetails() {        
    	return ResponseEntity.ok().body(userDetailService.readAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDetailDTO> getUserDetailById(@PathVariable("id") Long id) {

    	Optional<UserDetailDTO> currentEntityWrapper = userDetailService.readById(id);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
    	}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
    	Optional<UserDetail> currentEntityWrapper = this.userDetailService.create(userDetailDTO);
    	if (currentEntityWrapper.isPresent()) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("Created new user profile successfully"));
    	}
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> updateUserDetail(@PathVariable("id") Long id, @RequestBody UserDetailDTO userDetailDTO) {

    	Optional<UserDetail> currentEntityWrapper = this.userDetailService.update(id, userDetailDTO);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated user profile successfully!"));
    	}
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> deleteUserDetail(@PathVariable("id") Long id) {
    	if (this.userDetailService.delete(id)) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("item has been deleted successfully!"));
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }
}
