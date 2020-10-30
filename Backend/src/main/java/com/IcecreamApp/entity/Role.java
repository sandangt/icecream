package com.IcecreamApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.IcecreamApp.systemConstant.ERole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="roles")
public class Role extends Base implements ForeignConnection<Role> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2834524789864968814L;

	@Column(name="name", unique=true, columnDefinition="VARCHAR(20)")
	@Enumerated(EnumType.STRING)
	private ERole name;

	/**
	 * Foreign key section
	 */

	@ManyToMany(mappedBy="roles", fetch = FetchType.LAZY)
	@JsonIgnoreProperties("roles")
	private Set<User> users = new HashSet<>();
	
	public Role() {
		
	}
	
	public Role(Long id, ERole name) {
		this.id = id;
		this.name = name;
	}

	public ERole getName() {
		return name;
	}

	public void setCode(ERole name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users.addAll(users);
	}

	@Override
	public void setForeignKey(Role entity) {
		this.users.addAll(entity.users);
	}
}
