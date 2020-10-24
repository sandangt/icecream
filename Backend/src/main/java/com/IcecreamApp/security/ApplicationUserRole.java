package com.IcecreamApp.security;

import java.util.Set;

import com.google.common.collect.Sets;

public enum ApplicationUserRole {
	USER(Sets.newHashSet()),
	ADMIN(Sets.newHashSet(ApplicationUserPermission.USER_READ, 
			ApplicationUserPermission.USER_WRITE, 
			ApplicationUserPermission.PRODUCT_READ, 
			ApplicationUserPermission.PRODUCT_WRITE));
	
	private final Set<ApplicationUserPermission> permissions;

	ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
}
