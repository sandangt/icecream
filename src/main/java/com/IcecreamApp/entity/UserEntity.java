package com.IcecreamApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

	@Column(name = "username", unique = true, columnDefinition="VARCHAR(100)")
	private String userName;
	
	@Column(name = "email", unique = true, columnDefinition="VARCHAR(100)")
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name="status", columnDefinition = "TINYINT")
	private Integer status;
	
	/**
	 * Foreign key section
	 */
	
	private UserDetailEntity userDetail;
	
	private List<RoleEntity> roles = new ArrayList<>();
	
    
    private List<FeedbackEntity> feedbacks = new ArrayList<>();

    
    private List<OrderEntity> orders = new ArrayList<>();

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	public UserDetailEntity getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetailEntity userDetail) {
		this.userDetail = userDetail;
	}

	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<FeedbackEntity> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedbackEntity> feedbacks) {
		this.feedbacks = feedbacks;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}
    
}