package com.icecream.product.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.icecream.product.converter.UserDetailConverter;
import com.icecream.product.repository.UserDetailRepository;
import com.icecream.product.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icecream.product.DTO.UserDetailDTO;
import com.icecream.product.entity.User;
import com.icecream.product.entity.UserDetail;
import com.icecream.product.service.IUserDetailService;

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
		Optional<UserDetail> currentEntityWrapper = userDetailRepository.findById(id);
		if (currentEntityWrapper.isPresent())
			return Optional.ofNullable(UserDetailConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
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
