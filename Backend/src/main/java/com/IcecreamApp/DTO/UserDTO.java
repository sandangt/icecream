package com.IcecreamApp.DTO;

import java.util.Set;

public class UserDTO extends BaseDTO {

	private String username;
	
	private String email;

	private String password;
	
	private int status;
	
	private UserDetailDTO userDetail;

	private Set<RoleDTO> roles;

    private Set<FeedbackDTO> feedbacks;


    private Set<OrderDTO> orders;
	
	public UserDTO() {
	}

	public UserDTO(String username, String email, String password, int status, Set<RoleDTO> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.status = status;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	public Set<FeedbackDTO> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<FeedbackDTO> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(Set<OrderDTO> orders) {
		this.orders = orders;
	}
	
	
}
