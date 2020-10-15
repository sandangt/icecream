package com.IcecreamApp.dto;

import java.util.ArrayList;
import java.util.List;

import com.IcecreamApp.entity.RoleEntity;
import com.IcecreamApp.entity.UserDetailEntity;

public class UserDTO {

	private String userName;
	private String email;
	private String password;
	private List<RoleEntity> roles = new ArrayList<>();
	private Integer status;
	private UserDetailEntity userDetail;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public List<RoleEntity> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public UserDetailEntity getUserDetail() {
		return userDetail;
	}
	public void setUserDetail(UserDetailEntity userDetail) {
		this.userDetail = userDetail;
	}
}
