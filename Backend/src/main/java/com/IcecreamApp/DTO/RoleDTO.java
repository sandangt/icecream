package com.IcecreamApp.DTO;

import java.util.Date;

import com.IcecreamApp.systemConstant.ERole;

public class RoleDTO extends BaseDTO {
	
	private ERole name;

	public RoleDTO(long id, Date modifiedDate, ERole name) {
		super(id, modifiedDate);
		this.name = name;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

}
