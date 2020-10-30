package com.IcecreamApp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.service.IGeneralService;

public abstract class GeneralService<EntityType, RepositoryType extends JpaRepository<EntityType, Long>> implements IGeneralService<EntityType, RepositoryType> {
	
	@Autowired
	protected RepositoryType repository;
	
	protected String entityName;
	
	@Override
	public List<EntityType> readAll() {
		
    	return repository.findAll();
//        if (entities.isEmpty()) 
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@Override
	public EntityType readById(long id) {
    	return repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
    	
//        if (!currentEntityWrapper.isPresent())
//            return new ResponseEntity<>(currentEntityWrapper.get(), HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(currentEntityWrapper.get(), HttpStatus.OK);
	}

	@Override
	public EntityType create(EntityType entity) {
		return repository.save(entity);
//		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}

	@Override
	public EntityType update(long id, EntityType entity) {

//		Optional<EntityType> currentEntityWrapper = this.repository.findById(id);
//
//	    if (!currentEntityWrapper.isPresent()) {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	    this.repository.saveAndFlush(entity);
//		return new ResponseEntity<>(entity, HttpStatus.OK);
		
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		return this.repository.save(entity);
	}

	@Override
	public void delete(long id) {
//    	Optional<EntityType> currentEntityWrapper = repository.findById(id);
//        if (!currentEntityWrapper.isPresent())
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        EntityType currentEntity = currentEntityWrapper.get();
//        repository.delete(currentEntity);
//        return new ResponseEntity<>(currentEntity, HttpStatus.OK);
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		this.repository.deleteById(id);
	}
}
