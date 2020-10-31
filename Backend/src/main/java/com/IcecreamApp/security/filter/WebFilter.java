package com.IcecreamApp.security.filter;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.IcecreamApp.security.ApplicationUser;

@Component("WebFilter")
public class WebFilter {
	public boolean checkUserId(Authentication authentication, long id) {
		return ((ApplicationUser) authentication.getPrincipal()).getId() == id; 
	}
}
