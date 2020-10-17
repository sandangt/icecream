package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.IcecreamApp.service.IGeneralService;

public abstract class GeneralService<EntityType, RepositoryType extends JpaRepository<EntityType, Long>> implements IGeneralService<EntityType> {

	@Autowired
	RepositoryType repository;
	
	@Override
	public ResponseEntity<List<EntityType>> readAll() {
		
    	List<EntityType> entities = repository.findAll();
        if (entities.isEmpty()) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EntityType> readById(long id) {
    	Optional<EntityType> entity = this.repository.findById(id);

        if (!entity.isPresent())
            return new ResponseEntity<>(entity.get(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EntityType> create(EntityType entity) {
		repository.save(entity);
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}
}
