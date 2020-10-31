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

import com.IcecreamApp.DTO.RoleDTO;
import com.IcecreamApp.service.IRoleService;

@RestController
@RequestMapping("roles")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
    @GetMapping
    public List<RoleDTO> getAllRole() {        
    	return roleService.readAll();
    }

    @GetMapping(value = "/{id}")
    public RoleDTO getRoleById(@PathVariable("id") Long id) {
    	return roleService.readById(id);
    }
    
    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
    	return this.roleService.create(roleDTO);
    }

    @PutMapping(value = "/{id}")
    public RoleDTO updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) {
    	return this.roleService.update(id, roleDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteRole(@PathVariable("id") Long id) {
    	this.roleService.delete(id);
    }
}
