package com.IcecreamApp.DTO;

import java.util.Date;
import java.util.Set;

public class RoleDTO extends BaseDTO {
	

	private String code;

	private Set<UserDTO> users;
	
	public RoleDTO(long id, Date modifiedDate, String code, Set<UserDTO> users) {
		super(id, modifiedDate);
		this.code = code;
		this.users = users;
	}

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
