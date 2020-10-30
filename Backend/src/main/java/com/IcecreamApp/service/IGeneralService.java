package com.IcecreamApp.service;

import java.util.List;

public interface IGeneralService<EntityType, RepositoryType> {
		
	EntityType create(EntityType entity);
	
	public List<EntityType> readAll();

	public EntityType readById(long id);

	public EntityType update(long id, EntityType entity);

	public void delete(long id);
	
}