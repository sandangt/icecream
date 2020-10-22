package com.IcecreamApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User extends Base implements ForeignConnection<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5375733254179455598L;

	@Column(name = "username", unique = true, columnDefinition="VARCHAR(100)")
	private String username;
	
	@Column(name = "email", unique = true, columnDefinition="VARCHAR(100)")
	private String email;

	@Column(name = "password")
	private String password;
	
	@Column(name="status", columnDefinition = "TINYINT")
	private Integer status;
	
	/**
	 * Foreign key section
	 */

	@OneToOne(mappedBy="user",fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonIgnoreProperties("user")
	private UserDetail userDetail;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	@JsonIgnoreProperties("users")
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="feedback-user")
    private Set<Feedback> feedbacks = new HashSet<>();


	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="order-user")
    private Set<Order> orders = new HashSet<>();

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		if (userDetail == null) {
	        if (this.userDetail != null) {
	            this.userDetail.setUser(null);
	        }
		}
		else {
			userDetail.setUser(this);
		}
	    this.userDetail = userDetail;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<Feedback> feedbacks) {
		this.feedbacks.addAll(feedbacks);
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders.addAll(orders);
	}

	@Override
	public void setForeignKey(User entity) {
		this.userDetail = entity.userDetail;
		this.feedbacks = entity.feedbacks;
		this.orders = entity.orders;
	}
    
}