package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Role;
import com.IcecreamApp.repository.RoleRepository;

@Service
public class RoleService extends GeneralService<Role, RoleRepository> {

	public RoleService(RoleRepository repository) {
		super(repository);
	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	public ResponseEntity<Role> delete(long id) {
    	Optional<Role> currentRoleWrapper = repository.findById(id);
        if (!currentRoleWrapper.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Role currentRole = currentRoleWrapper.get();
        currentRole.getUsers().remove(currentRole);
        repository.delete(currentRole);
        return new ResponseEntity<>(currentRole, HttpStatus.OK);
	}
}