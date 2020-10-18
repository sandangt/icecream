package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.service.IUserDetailService;

@RestController
public class UserDetailController {
	
	@Autowired
	private IUserDetailService userDetailService;

    @GetMapping(value = "/user-details")
    public ResponseEntity<List<UserDetail>> getAllUser() {        
    	return userDetailService.readAll();
    }

    @GetMapping(value = "/user-details/{id}")
    public ResponseEntity<UserDetail> getUserById(@PathVariable("id") Long id) {
    	return userDetailService.readById(id);
    }

    @PostMapping(value = "/user-details")
    public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail userDetail) {
    	return this.userDetailService.create(userDetail);
    }

    @PutMapping(value = "/user-details/{id}")
    public ResponseEntity<UserDetail> updateUser(@PathVariable("id") Long id, @RequestBody UserDetail userDetail) {
    	return this.userDetailService.update(id, userDetail);
    }

    @DeleteMapping(value = "/user-details/{id}")
    public ResponseEntity<UserDetail> deleteUser(@PathVariable("id") Long id) {
    	return this.userDetailService.delete(id);
    }
}
