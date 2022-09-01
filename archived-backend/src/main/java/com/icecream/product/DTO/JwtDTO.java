package com.icecream.product.DTO;

import java.util.List;

public class JwtDTO {
	
	private Long id;
	private String token;
	private String tokenPrefix;
	private String username;
	private String email;
	private List<String> roles;

	public JwtDTO(Long id, String token, String tokenPrefix, String username, String email, List<String> roles) {
		this.id = id;
		this.token = token;
		this.tokenPrefix = tokenPrefix;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenPrefix() {
		return tokenPrefix;
	}

	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
