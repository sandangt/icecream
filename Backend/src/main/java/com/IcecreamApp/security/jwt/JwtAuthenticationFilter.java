package com.IcecreamApp.security.jwt;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.IcecreamApp.payload.request.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
    private AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	
    	LoginRequest loginCredentials = null;
    	
    	try {
    		loginCredentials = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        		loginCredentials.getUsername(),
        		loginCredentials.getPassword(),
                new HashSet<>());
        
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    	
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, 
    		HttpServletResponse response, 
    		FilterChain chain, 
    		Authentication authResult) throws IOException, ServletException {
    	
    	// Generate token
        String token = this.jwtUtils.generateJwtToken(authResult);

        // Add token in response
        response.addHeader(this.jwtUtils.HEADER_STRING, this.jwtUtils.TOKEN_PREFIX + token);
    }
}