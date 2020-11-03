package com.IcecreamApp.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.JwtDTO;
import com.IcecreamApp.DTO.LoginRequestDTO;
import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.SignupRequestDTO;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import com.IcecreamApp.entity.Role;
import com.IcecreamApp.entity.User;
import com.IcecreamApp.repository.RoleRepository;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.security.ApplicationUser;
import com.IcecreamApp.security.filter.JwtUtils;
import com.IcecreamApp.service.IAuthenticationService;
import com.IcecreamApp.systemConstant.ERole;
import com.IcecreamApp.systemConstant.EStatus;

@Service
public class AuthentcationService implements IAuthenticationService {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private JwtUtils jwtUtils;
	
	@Override
	public ResponseEntity<JwtDTO> login(LoginRequestDTO loginRequest, HttpServletResponse response) {

	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	    		loginRequest.getUsername(),
	    		loginRequest.getPassword(),
	            new HashSet<>());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtils.generateJwtToken(authentication);
		
		ApplicationUser appUser = (ApplicationUser) authentication.getPrincipal();		
		List<String> roles = appUser.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
	
		return new ResponseEntity<JwtDTO>(new JwtDTO(jwtToken, jwtUtils.TOKEN_PREFIX, appUser.getUsername(), appUser.getEmail(), roles), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> signup(SignupRequestDTO signupRequest) {
		if (userRepository.existsByUsername(signupRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDTO("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signupRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponseDTO("Error: Email is already in use!"));
		}


//		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} 
//		else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "ADMIN":
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				case "STAFF":
//					Role modRole = roleRepository.findByName(ERole.ROLE_STAFF)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(modRole);
//
//					break;
//				default:
//					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//					break;
//				}
//			});
//		}

		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		User user = new User(null, signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()),
				EStatus.AVAILABLE,new HashSet<Role>());
		
		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<MessageResponseDTO>(new MessageResponseDTO("User registered successfully!"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<MessageResponseDTO> logout(HttpServletRequest request, HttpServletResponse response) {
        
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return new ResponseEntity<MessageResponseDTO>(new MessageResponseDTO("Logout successfully!"), HttpStatus.OK);
        }
        return new ResponseEntity<MessageResponseDTO>(new MessageResponseDTO("Logout unsuccessfully"), HttpStatus.NOT_FOUND);
	}

}
