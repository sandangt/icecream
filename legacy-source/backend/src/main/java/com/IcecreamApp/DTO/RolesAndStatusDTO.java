package com.IcecreamApp.DTO;

import java.util.Set;

import com.IcecreamApp.systemConstant.EStatus;

public class RolesAndStatusDTO {
	private Set<RoleDTO> roles;
	private EStatus status;
	public RolesAndStatusDTO(Set<RoleDTO> roles, EStatus status) {
		this.roles = roles;
		this.status = status;
	}
	public Set<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
	public EStatus getStatus() {
		return status;
	}
	public void setStatus(EStatus status) {
		this.status = status;
	}
	
}
