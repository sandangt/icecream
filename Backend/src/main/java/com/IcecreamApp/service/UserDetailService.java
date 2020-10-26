package com.IcecreamApp.service;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.repository.UserDetailRepository;

@Service
public class UserDetailService extends GeneralService<UserDetail, UserDetailRepository> {

	public UserDetailService(UserDetailRepository repository) {
		super(repository);
		entityName = "user detail";
	}
	
}
