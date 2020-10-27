package com.IcecreamApp.security;

import java.util.Collection;
import java.util.HashSet;
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

//	private final List<? extends GrantedAuthority> grantedAuthorities;
//	private final String password;
//	private final String username;
//	private final boolean isAccountNonExpired;
//	private final boolean isAccountNonLocked;
//	private final boolean isCredentialsNonExpired;
//	private final boolean isEnabled;
	
	private User user;
	
	public ApplicationUser(User user) {
		this.user = user;
	}
	
//	public ApplicationUser(List<? extends GrantedAuthority> grantedAuthorities, 
//			String password, 
//			String username,
//			boolean isAccountNonExpired, 
//			boolean isAccountNonLocked, 
//			boolean isCredentialsNonExpired,
//			boolean isEnabled) {
//		super();
//		this.grantedAuthorities = grantedAuthorities;
//		this.password = password;
//		this.username = username;
//		this.isAccountNonExpired = isAccountNonExpired;
//		this.isAccountNonLocked = isAccountNonLocked;
//		this.isCredentialsNonExpired = isCredentialsNonExpired;
//		this.isEnabled = isEnabled;
//	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		this.user.getRoles().forEach( i -> {
			GrantedAuthority authority = new SimpleGrantedAuthority(i.getCode());
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
		return user.getStatus() == 0 ? false : true;
	}

}
