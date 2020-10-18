package com.IcecreamApp.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.repository.UserDetailRepository;
import com.IcecreamApp.service.IUserDetailService;

@Service
public class UserDetailService extends GeneralService<UserDetail, UserDetailRepository> implements IUserDetailService {

	@Override
	public ResponseEntity<UserDetail> delete(long id) {
    	Optional<UserDetail> currentUserDetailWrapper = repository.findById(id);
        if (!currentUserDetailWrapper.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        UserDetail currentUserDetail = currentUserDetailWrapper.get();
        repository.delete(currentUserDetail);
        return new ResponseEntity<>(currentUserDetail, HttpStatus.OK);
	}

}
