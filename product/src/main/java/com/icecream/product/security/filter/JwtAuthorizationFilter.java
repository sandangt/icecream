package com.icecream.product.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icecream.product.security.ApplicationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
	

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private ApplicationUserService applicationUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
	    String jwtToken = parseJwt(request);
	    try {
	    	if (jwtToken != null && jwtUtils.validateJwtToken(jwtToken)) {
	    		String username = jwtUtils.getUserNameFromJwtToken(jwtToken);
	    		UserDetails userDetails = applicationUserService.loadUserByUsername(username);
	    		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	    				userDetails,null,userDetails.getAuthorities());
	    		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
	    	}
	    }
	    catch (Exception e) {
	    	logger.error("Cannot set user authentication: {}", e);
	    }
	    chain.doFilter(request, response);
	}
	
	private String parseJwt(HttpServletRequest request) {
		String header = request.getHeader(jwtUtils.HEADER_STRING);
		if (StringUtils.hasText(header) && header.startsWith(jwtUtils.TOKEN_PREFIX)) {
			return header.replace(jwtUtils.TOKEN_PREFIX,"");
		}
		return null;
	}

}
