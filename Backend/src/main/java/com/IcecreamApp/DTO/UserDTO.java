package com.IcecreamApp.DTO;

import java.util.Date;
import java.util.Set;

import com.IcecreamApp.systemConstant.EStatus;

public class UserDTO extends BaseDTO {

	private String username;
	
	private String email;

	private String password;
	
	private EStatus status;
	
	private UserDetailDTO userDetail;

	private Set<RoleDTO> roles;

	public UserDTO(long id, Date modifiedDate, String username, String email, String password, EStatus status,
			UserDetailDTO userDetail, Set<RoleDTO> roles) {
		super(id, modifiedDate);
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
		this.userDetail = userDetail;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public UserDetailDTO getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetailDTO userDetail) {
		this.userDetail = userDetail;
	}

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}

}
