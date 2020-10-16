package com.IcecreamApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.UserEntity;
import com.IcecreamApp.service.IUserService;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserEntity>> getAllUser() {        
    	
    	List<UserEntity> users = userService.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
		return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Long id) {
    	Optional<UserEntity> user = userService.findById(id);

        if (!user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
    	userService.save(user);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

//    @PutMapping(value = "/user/{id}")
//    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long id, @RequestBody UserEntity user) {
//    	Optional<UserEntity> currentUser = userService.findById(id);
//
//        if (!currentUser.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        currentUser.get().setUserName(user.getUserName());
//        currentUser.get().setEmail(user.getEmail());
//        currentUser.get().setDescription(product.getDescription());
//
//        productService.save(currentProduct.get());
//        return new ResponseEntity<>(currentProduct.get(), HttpStatus.OK);
//    }

//    @DeleteMapping(value = "/user/{id}")
//    public ResponseEntity<UserEntity> deleteUser@PathVariable("id") Long id) {
//    	
//    	Optional<UserEntity> user = userService.findById(id);
//        if (!user.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        productService.remove(product.get());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}