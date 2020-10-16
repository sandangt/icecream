package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.entity.UserEntity;

public interface IUserService {
	
	List<UserEntity> findAll();
	
 	Optional<UserEntity> findById(long id);   
 	
 	void save(UserEntity user);    
 	
 	void delete(UserEntity user);
	
}
