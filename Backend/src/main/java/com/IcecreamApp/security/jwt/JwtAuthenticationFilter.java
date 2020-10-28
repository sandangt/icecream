package com.IcecreamApp.security.jwt;

import java.io.IOException;
import java.util.Date;
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

import com.IcecreamApp.DTO.LoginDTO;
import com.IcecreamApp.security.ApplicationUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	
    	LoginDTO loginCredentials = null;
    	
    	try {
    		loginCredentials = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);
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
    	
        ApplicationUser applicationUser = (ApplicationUser) authResult.getPrincipal();
        // Create JWT Token
        String token = JWT.create()
                .withSubject(applicationUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        // Add token in response
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + token);
    }
}