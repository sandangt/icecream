package com.IcecreamApp.DTO;

import java.util.Set;

public class RoleDTO extends BaseDTO {
	

	private String code;

	/**
	 * Foreign key section
	 */

	private Set<UserDTO> users;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<UserDTO> getUsers() {
		return users;
	}

	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}
	
}
