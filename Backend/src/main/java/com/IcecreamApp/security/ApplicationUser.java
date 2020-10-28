package com.IcecreamApp.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.IcecreamApp.entity.User;

public class ApplicationUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1836394355611648180L;
	
	private User user;
	
	public ApplicationUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		this.user.getRoles().forEach( i -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(i.getName().name());
			authorities.add(authority);
		});
		return authorities;	
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.getStatus() == 1;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ApplicationUser appUser = (ApplicationUser) o;
		return Objects.equals(this.getUsername(), appUser.getUsername());
	}
}
