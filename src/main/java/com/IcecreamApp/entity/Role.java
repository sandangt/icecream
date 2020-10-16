package com.IcecreamApp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="roles")
public class Role extends Base {
	
	@Column(name="code", unique=true, columnDefinition="VARCHAR(15)")
	private String code;

	/**
	 * Foreign key section
	 */

	@ManyToMany(mappedBy="roles")
	@JsonIgnore
	private List<User> users;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
