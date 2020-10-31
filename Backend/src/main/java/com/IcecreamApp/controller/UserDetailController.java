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

import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.service.IUserDetailService;

@RestController
@RequestMapping("user-details")
public class UserDetailController {
	
	@Autowired
	private IUserDetailService userDetailService;

    @GetMapping
    public List<UserDetailDTO> getAllUserDetails() {        
    	return userDetailService.readAll();
    }

    @GetMapping(value = "/{id}")
    public UserDetailDTO getUserDetailById(@PathVariable("id") Long id) {
    	return userDetailService.readById(id);
    }

    @PostMapping
    public UserDetailDTO createUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
    	return this.userDetailService.create(userDetailDTO);
    }

    @PutMapping(value = "/{id}")
    public UserDetailDTO updateUserDetail(@PathVariable("id") Long id, @RequestBody UserDetailDTO userDetailDTO) {
    	return this.userDetailService.update(id, userDetailDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUserDetail(@PathVariable("id") Long id) {
    	this.userDetailService.delete(id);
    }
}
