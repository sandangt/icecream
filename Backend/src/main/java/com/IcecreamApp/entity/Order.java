package com.IcecreamApp.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="orders")
public class Order extends Base implements ForeignConnection<Order>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5185509684141990101L;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="code", columnDefinition = "INT")
	private Long code;
	
	@Column(name="paymentmethod", columnDefinition="VARCHAR(100)")
	private String paymentMethod;
	
	@Column(name="status", columnDefinition="TINYINT")
	private Integer status;
	
	/**
	 * Foreign key section
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonBackReference(value="order-user")
	private User user;	

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="order_detail-order")
	private Set<OrderDetail> orderDetails = new HashSet<>();

	public Order() {
	}
	
	public Order(long code, String paymentMethod, int status, User user) {
		this.code = code;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.user = user;
	}
	
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		System.out.println(user.getUserName());
		this.user = user;
	}

	public Set<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails.addAll(orderDetails);
	}

	@Override
	public void setForeignKey(Order entity) {
		this.orderDetails.addAll(entity.orderDetails);
	}
}
