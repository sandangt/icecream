package com.IcecreamApp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.JwtDTO;
import com.IcecreamApp.DTO.LoginRequestDTO;
import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.SignupRequestDTO;
import com.IcecreamApp.service.IAuthenticationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private IAuthenticationService authenticationService;

	@PostMapping(value = "/login")
	public ResponseEntity<JwtDTO> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
		return authenticationService.login(loginRequest, response);
	}

	@PostMapping(value = "/signup")
	public ResponseEntity<MessageResponseDTO> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
		return authenticationService.signup(signUpRequest);
	}

    @GetMapping(value = "/logout")
    public ResponseEntity<MessageResponseDTO> logout(HttpServletRequest request, HttpServletResponse response) {
    	return authenticationService.logout(request, response);
    }
}