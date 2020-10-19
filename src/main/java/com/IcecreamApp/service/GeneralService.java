package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class GeneralService<EntityType, RepositoryType extends JpaRepository<EntityType, Long>> {

	protected final RepositoryType repository;
	
	public GeneralService(RepositoryType repository) {
		this.repository = repository;
	}
		
	public ResponseEntity<List<EntityType>> readAll() {
		
    	List<EntityType> entities = repository.findAll();
        if (entities.isEmpty()) 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	public ResponseEntity<EntityType> readById(long id) {
    	Optional<EntityType> entity = this.repository.findById(id);

        if (!entity.isPresent())
            return new ResponseEntity<>(entity.get(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity.get(), HttpStatus.OK);
	}

	public ResponseEntity<EntityType> create(EntityType entity) {
		repository.saveAndFlush(entity);
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}


	public ResponseEntity<EntityType> update(long id, EntityType entity) {

		Optional<EntityType> currentEntityWrapper = this.repository.findById(id);

	    if (!currentEntityWrapper.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    EntityType currentEntity = currentEntityWrapper.get();
	    BeanUtils.copyProperties(entity, currentEntity);
	    this.repository.save(currentEntity);
		return new ResponseEntity<>(currentEntity, HttpStatus.OK);
	}

	public ResponseEntity<EntityType> delete(long id) {
    	Optional<EntityType> currentEntityWrapper = repository.findById(id);
        if (!currentEntityWrapper.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        EntityType currentEntity = currentEntityWrapper.get();
        repository.delete(currentEntity);
        return new ResponseEntity<>(currentEntity, HttpStatus.OK);
	}
}
