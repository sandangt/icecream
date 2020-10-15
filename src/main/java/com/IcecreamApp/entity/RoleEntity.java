package com.IcecreamApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class RoleEntity extends BaseEntity {
	
	@Column(name="code", unique=true, columnDefinition="VARCHAR(15)")
	private String code;
	
	/**
	 * Foreign key section
	 */
	@ManyToMany(mappedBy="roles")
	private List<UserEntity> users = new ArrayList<>();
}
