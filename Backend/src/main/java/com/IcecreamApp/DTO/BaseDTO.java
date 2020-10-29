package com.IcecreamApp.DTO;

import java.util.Date;

public abstract class BaseDTO {
	
	private long id;
	private Date modifiedDate;

	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
}