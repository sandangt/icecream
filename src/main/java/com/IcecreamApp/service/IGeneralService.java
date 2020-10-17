package com.IcecreamApp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IGeneralService<EntityType> {
	
	ResponseEntity<List<EntityType>> readAll();
	
 	ResponseEntity<EntityType> readById(long id);   
 	
 	ResponseEntity<EntityType> create(EntityType entity);
 	
 	ResponseEntity<EntityType> update(long id, EntityType entity);
 	
 	ResponseEntity<EntityType> delete(long id);
	
}
