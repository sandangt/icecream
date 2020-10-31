package com.IcecreamApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.UserRepository;

@Service
public class ApplicationUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow( () -> new ResourceNotFoundException(String.format("user %s not found", username)));
		// user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		ApplicationUser applicationUser = new ApplicationUser(user);
		return applicationUser;
	} 

	
}
