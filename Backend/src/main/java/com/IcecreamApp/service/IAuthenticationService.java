package com.IcecreamApp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;

import com.IcecreamApp.DTO.JwtDTO;
import com.IcecreamApp.DTO.LoginRequestDTO;
import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.SignupRequestDTO;

public interface IAuthenticationService {

	ResponseEntity<JwtDTO> login(LoginRequestDTO loginRequest, HttpServletResponse response);

	ResponseEntity<MessageResponseDTO> signup(SignupRequestDTO signupRequest);
	
	ResponseEntity<MessageResponseDTO> logout(HttpServletRequest request, HttpServletResponse response);
}
