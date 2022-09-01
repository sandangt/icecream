package com.icecream.product.security;

import java.util.Optional;

import com.icecream.product.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.icecream.product.entity.User;

@Service
public class ApplicationUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	private static final Logger logger = LoggerFactory.getLogger(ApplicationUserService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userWrapper = userRepository.findByUsername(username);
		if (userWrapper.isPresent()) {
			ApplicationUser applicationUser = new ApplicationUser(userWrapper.get());
			return applicationUser;
		}
		logger.error(String.format("user %s not found", username));
		return null;
	} 
	
}
