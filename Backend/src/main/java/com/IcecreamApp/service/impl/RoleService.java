package com.IcecreamApp.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Role;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.RoleRepository;
import com.IcecreamApp.service.IRoleService;

@Service
public class RoleService extends GeneralService<Role, RoleRepository> implements IRoleService {

	public RoleService() {
		entityName = "role";
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
	public Role update(long id, Role entity) {

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
		entity.setForeignKey(currentEntityWrapper.get());
		return this.repository.save(entity);
	}
}