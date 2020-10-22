package com.IcecreamApp.DTO;

import java.util.Date;

public abstract class BaseDTO {
	
	private Long id;
	private Date modifiedDate;

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}
