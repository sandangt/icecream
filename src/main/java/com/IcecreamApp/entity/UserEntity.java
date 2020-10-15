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
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL)
	private UserDetailEntity userDetail;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleEntity> roles = new ArrayList<>();
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedbackEntity> feedbacks = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();
}