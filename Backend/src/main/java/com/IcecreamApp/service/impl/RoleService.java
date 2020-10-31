package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.RoleDTO;
import com.IcecreamApp.converter.RoleConverter;
import com.IcecreamApp.entity.Role;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.RoleRepository;
import com.IcecreamApp.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository repository;
	
	private String entityName = "Role";
	
	Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	@Override
	public List<RoleDTO> readAll() {
		
		List<RoleDTO> roles = new ArrayList<>();
		
    	for (Role i : repository.findAll()) {
    		roles.add(RoleConverter.toDTO(i));
    	}
    	return roles;
	}

	@Override
	public RoleDTO readById(long id) {
    	Role role = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
    	return RoleConverter.toDTO(role);
	}

	@Override
	public RoleDTO create(RoleDTO roleDTO) {
		repository.save(RoleConverter.toEntity(roleDTO));
		return roleDTO;
	}	
	
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void delete(long id) {
//    	Optional<Role> currentRoleWrapper = repository.findById(id);
//        if (!currentRoleWrapper.isPresent())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        Role currentRole = currentRoleWrapper.get();
//        currentRole.getUsers().remove(currentRole);
//        repository.delete(currentRole);
//        return new ResponseEntity<>(currentRole, HttpStatus.OK);
        
        Optional<Role> currentRoleWrapper = repository.findById(id);
		if (!currentRoleWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
        Role currentRole = currentRoleWrapper.get();
        currentRoleWrapper.get().getUsers().remove(currentRole);
        repository.delete(currentRole);
	}

	@Override
	public RoleDTO update(long id, RoleDTO roleDTO) {

//		Optional<Role> currentEntityWrapper = this.repository.findById(id);
//
//	    if (!currentEntityWrapper.isPresent()) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    entity.setForeignKey(currentEntityWrapper.get());
//	    System.out.println(currentEntityWrapper.get().getUsers());
//	    this.repository.saveAndFlush(entity);
//		return new ResponseEntity<>(entity, HttpStatus.OK);
		
		Optional<Role> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		Role role = RoleConverter.toEntity(roleDTO);
		role.setForeignKey(currentEntityWrapper.get());
		this.repository.save(role);
		return roleDTO;
	}
}