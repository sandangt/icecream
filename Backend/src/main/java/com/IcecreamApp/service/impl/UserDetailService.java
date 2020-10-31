package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.converter.UserDetailConverter;
import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.UserDetailRepository;
import com.IcecreamApp.service.IUserDetailService;

@Service
public class UserDetailService implements IUserDetailService {

	
	@Autowired
	private UserDetailRepository repository;

	private String entityName = "User detail";
	
	@Override
	public List<UserDetailDTO> readAll() {
		
		List<UserDetailDTO> userDetails = new ArrayList<>();
		for (UserDetail i : repository.findAll()) {
			userDetails.add(UserDetailConverter.toDTO(i));
		}
    	return userDetails;
	}

	@Override
	public UserDetailDTO readById(long id) {
		
		UserDetail userDetail = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		return UserDetailConverter.toDTO(userDetail);
	}

	@Override
	public UserDetailDTO create(UserDetailDTO userDetailDTO) {
		
		repository.save(UserDetailConverter.toEntity(userDetailDTO));
		return userDetailDTO;
	}

	@Override
	public UserDetailDTO update(long id, UserDetailDTO userDetailDTO) {

		Optional<UserDetail> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		UserDetail userDetail = UserDetailConverter.toEntity(userDetailDTO);
		this.repository.save(userDetail);
		return userDetailDTO;
	}

	@Override
	public void delete(long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		this.repository.deleteById(id);
	}
	
	
	
}
