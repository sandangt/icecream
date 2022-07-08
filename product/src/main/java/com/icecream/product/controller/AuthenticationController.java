package com.icecream.product.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.icecream.product.DTO.LoginRequestDTO;
import com.icecream.product.DTO.MessageResponseDTO;
import com.icecream.product.DTO.SignupRequestDTO;
import com.icecream.product.entity.Role;
import com.icecream.product.entity.User;
import com.icecream.product.repository.RoleRepository;
import com.icecream.product.repository.UserRepository;
import com.icecream.product.security.ApplicationUser;
import com.icecream.product.security.filter.JwtUtils;
import com.icecream.product.systemConstant.ERole;
import com.icecream.product.systemConstant.EStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.icecream.product.DTO.JwtDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
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
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@PostMapping(value = "/login")
	public ResponseEntity<JwtDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
	    		loginRequest.getUsername(),
	    		loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtUtils.generateJwtToken(authentication);
		
		ApplicationUser appUser = (ApplicationUser) authentication.getPrincipal();
		List<String> roles = appUser.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
	
		return new ResponseEntity<JwtDTO>(new JwtDTO(appUser.getId(), jwtToken, jwtUtils.TOKEN_PREFIX, appUser.getUsername(), appUser.getEmail(), roles), HttpStatus.OK);
	}

	@PostMapping(value = "/signup")
	public ResponseEntity<MessageResponseDTO> registerUser(@Valid @RequestBody SignupRequestDTO signupRequest) {
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

		Set<Role> roles = new HashSet<>();

		Optional<Role> roleWrapper = roleRepository.findByName(ERole.ROLE_USER);
		if (roleWrapper.isPresent()) {
			roles.add(roleWrapper.get());
			User user = new User(null, signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()),
					EStatus.AVAILABLE,new HashSet<Role>());
			user.setRoles(roles);
			userRepository.save(user);
			return new ResponseEntity<MessageResponseDTO>(new MessageResponseDTO("User registered successfully!"), HttpStatus.OK);
		}
		logger.error("Invalid role");
		return new ResponseEntity<MessageResponseDTO>(new MessageResponseDTO("Signup failed!"), HttpStatus.NOT_FOUND);
	}

    @GetMapping(value = "/logout")
    public ResponseEntity<MessageResponseDTO> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return new ResponseEntity<MessageResponseDTO>(new MessageResponseDTO("Logout successfully!"), HttpStatus.OK);
        }
        return new ResponseEntity<MessageResponseDTO>(new MessageResponseDTO("Logout unsuccessfully!"), HttpStatus.NOT_FOUND);
	}
}
