package com.IcecreamApp.service.impl;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.repository.UserDetailRepository;
import com.IcecreamApp.service.IUserDetailService;

@Service
public class UserDetailService extends GeneralService<UserDetail, UserDetailRepository> implements IUserDetailService {

	public UserDetailService() {
		entityName = "user detail";
	}
	
}
