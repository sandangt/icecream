package com.IcecreamApp.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.IcecreamApp.entity.User;
import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.security.ApplicationUser;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	
	private final UserRepository userRepository;
	private final JwtUtils jwtUtils;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository, JwtUtils jwtUtils) {
		super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
	    // Read the Authorization header, where the JWT token should be
	    String header = request.getHeader(jwtUtils.HEADER_STRING);
	
	    // If header does not contain BEARER or is null delegate to Spring impl and exit
	    if (header == null || !header.startsWith(jwtUtils.TOKEN_PREFIX)) {
	        chain.doFilter(request, response);
	        return;
	    }
	
	    // If header is present, try grab user principal from database and perform authorization
	    Authentication authentication = this.getUsernamePasswordAuthentication(request);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	
	    // Continue filter execution
	    chain.doFilter(request, response);
	}
	
	private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
	    String token = request.getHeader(jwtUtils.HEADER_STRING)
	            .replace(jwtUtils.TOKEN_PREFIX,"");
	
	    if (token != null) {
	        // parse the token and validate it
	    	String username = jwtUtils.getDecodedBody(token).getSubject();
	
	        // Search in the DB if we find the user by token subject (username)
	        // If so, then grab user details and create spring auth token using username, pass, authorities/roles
	        if (username != null) {
	            User user = userRepository.findByUsername(username).get();
	            ApplicationUser applicationUser = new ApplicationUser(user);
	            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, applicationUser.getAuthorities());
	            return auth;
	        }
	        return null;
	    }
	    return null;
	}

}
