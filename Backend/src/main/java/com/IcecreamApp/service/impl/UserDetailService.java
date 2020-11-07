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
import com.IcecreamApp.entity.User;
import com.IcecreamApp.entity.UserDetail;
import com.IcecreamApp.repository.UserDetailRepository;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.service.IUserDetailService;

@Service
public class UserDetailService implements IUserDetailService {

	
	@Autowired
	private UserDetailRepository userDetailRepository;
	@Autowired
	private UserRepository userRepository;

	private String entityName = "User detail";
	
	private static final Logger logger = LoggerFactory.getLogger(UserDetailService.class);
	
	@Override
	public List<UserDetailDTO> readAll() {
    	return userDetailRepository.findAll().stream().map(UserDetailConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<UserDetailDTO> readById(long id) {
		UserDetail userDetail = userDetailRepository.findById(id).get();
		return Optional.ofNullable(UserDetailConverter.toDTO(userDetail));
	}

	@Override
	public Optional<UserDetail> create(UserDetailDTO userDetailDTO) {
		Optional<User> currentEntityWrapper = userRepository.findById(userDetailDTO.getId());
		if (currentEntityWrapper.isPresent()) {
			return Optional.ofNullable(userDetailRepository.save(UserDetailConverter.toEntity(userDetailDTO)));
		}
		logger.error(String.format("%s id %d not found", entityName, userDetailDTO.getId()));
		return Optional.empty();
	}

	@Override
	public Optional<UserDetail> update(long id, UserDetailDTO userDetailDTO) {

		Optional<User> currentEntityWrapper = userRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			userDetailDTO.setId(id);
			return Optional.ofNullable(this.userDetailRepository.save(UserDetailConverter.toEntity(userDetailDTO)));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<UserDetail> currentEntityWrapper = userDetailRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.userDetailRepository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}
	
}
