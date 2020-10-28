package com.IcecreamApp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.utils.PasswordConfig;

@Service
public class ApplicationUserService implements UserDetailsService {

	private UserRepository userRepository;
	
	public ApplicationUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow( () -> new ResourceNotFoundException(String.format("user %s not found", username)));
		user.setPassword(PasswordConfig.passwordEncoder().encode(user.getPassword()));
		ApplicationUser applicationUser = new ApplicationUser(user);
		return applicationUser;
	} 

	
}
