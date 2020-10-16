package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.UserEntity;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	@Override
	public List<UserEntity> findAll() {
		return (List<UserEntity>) userRepository.findAll();
	}

	@Override
	public Optional<UserEntity> findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	public void save(UserEntity user) {
		userRepository.save(user);
		
	}

	@Override
	public void delete(UserEntity user) {
		userRepository.delete(user);
	}
}
