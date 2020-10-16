package com.IcecreamApp.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Base {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@Column
//	private String createdBy;
//	
//	@Column
//	private Date createdDate;
//	
//	@Column
//	private String modifiedBy;
//	
//	@Column
//	private Date modifiedDate;

	public Long getId() {
		return id;
	}
}