package com.IcecreamApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class OrderEntity extends BaseEntity {
	@Column(name="paymentmethod", columnDefinition="VARCHAR(100)")
	private String paymentMethod;
	
	@Column(name="status", columnDefinition="TINYINT")
	private Integer status;
	
	/**
	 * Foreign key section
	 */
	private UserEntity user;	
    
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<OrderDetailEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
