package com.IcecreamApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="roles")
public class Role extends Base implements ForeignConnection<Role> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2834524789864968814L;

	@Column(name="code", unique=true, columnDefinition="VARCHAR(15)")
	private String code;

	/**
	 * Foreign key section
	 */

	@ManyToMany(mappedBy="roles", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("roles")
	private Set<User> users = new HashSet<>();
	
	public Role() {
	}
	
	public Role(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public void setForeignKey(Role entity) {
		this.users = entity.users;
	}
}
