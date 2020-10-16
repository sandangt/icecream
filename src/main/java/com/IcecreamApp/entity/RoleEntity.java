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
	
	private List<UserEntity> users = new ArrayList<>();
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToMany(mappedBy="roles")
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
}
