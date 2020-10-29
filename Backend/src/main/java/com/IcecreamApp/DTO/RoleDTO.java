package com.IcecreamApp.DTO;

import com.IcecreamApp.systemConstant.ERole;

public class RoleDTO extends BaseDTO {
	

	private ERole name;

	public ERole getCode() {
		return name;
	}

	public void setCode(ERole name) {
		this.name = name;
	}

}
