package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Role;
import com.IcecreamApp.service.RoleService;

@RestController
public class RoleController {

	private RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}
	
    @GetMapping(value = "/roles")
    public ResponseEntity<List<Role>> getAllRole() {        
    	return roleService.readAll();
    }

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
    	return roleService.readById(id);
    }
    
    @PostMapping(value = "/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
    	return this.roleService.create(role);
    }

    @PutMapping(value = "/roles/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable("id") Long id, @RequestBody Role role) {
    	return this.roleService.update(id, role);
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<Role> deleteRole(@PathVariable("id") Long id) {
    	return this.roleService.delete(id);
    }
}
