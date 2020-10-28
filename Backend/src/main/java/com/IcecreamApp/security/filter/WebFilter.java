package com.IcecreamApp.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("WebFilter")
public class WebFilter {
	public boolean checkUserId(Authentication authentication, long id) {
		return (long) authentication.getCredentials() == id; 
	}
}
