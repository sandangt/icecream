package com.IcecreamApp.DTO;

import java.util.Date;
import java.util.Set;

public class RoleDTO extends BaseDTO {
	

	private String code;

	private Set<String> user_names;
	
	public RoleDTO(long id, Date modifiedDate, String code, Set<String> user_names) {
		super(id, modifiedDate);
		this.code = code;
		this.user_names = user_names;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setUsers(Set<String> user_names) {
		this.user_names.addAll(user_names);
	}
	
}
