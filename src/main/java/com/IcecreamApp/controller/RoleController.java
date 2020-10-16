package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Role;
import com.IcecreamApp.repository.RoleRepository;

@RestController
public class RoleController {
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping(value="/role")
	public List<Role> findAllRole() {
		return roleRepository.findAll();
	}
}
