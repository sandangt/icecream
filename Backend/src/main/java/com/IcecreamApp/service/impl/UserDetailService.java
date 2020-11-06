package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.UserDetailDTO;
import com.IcecreamApp.converter.UserDetailConverter;
import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.repository.UserDetailRepository;
import com.IcecreamApp.service.IUserDetailService;

@Service
public class UserDetailService implements IUserDetailService {

	
	@Autowired
	private UserDetailRepository repository;

	private String entityName = "User detail";
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);
	
	@Override
	public List<UserDetailDTO> readAll() {
    	return repository.findAll().stream().map(UserDetailConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<UserDetailDTO> readById(long id) {
		UserDetail userDetail = repository.findById(id).get();
		return Optional.ofNullable(UserDetailConverter.toDTO(userDetail));
	}

	@Override
	public UserDetail create(UserDetailDTO userDetailDTO) {
		return repository.save(UserDetailConverter.toEntity(userDetailDTO));
	}

	@Override
	public Optional<UserDetail> update(long id, UserDetailDTO userDetailDTO) {

		Optional<UserDetail> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return Optional.ofNullable(this.repository.save(UserDetailConverter.toEntity(userDetailDTO)));
	    }
		logger.error(String.format("%s id %ld not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<UserDetail> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %ld not found", entityName, id));
		return false;
	}
	
}
