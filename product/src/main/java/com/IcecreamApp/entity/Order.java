package com.IcecreamApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.IcecreamApp.systemConstant.EStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="orders")
public class Order extends Base implements ForeignConnection<Order>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5185509684141990101L;

//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="code", columnDefinition = "VARCHAR(250)", unique = true)
	private String code;
	
	@Column(name="payment_method", columnDefinition="VARCHAR(100)")
	private String paymentMethod;
	
	@Column(name="status")
	@Enumerated(EnumType.ORDINAL)
	private EStatus status;
	
	/**
	 * Foreign key section
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	@JsonBackReference(value="order-user")
	private User user;	

	@OneToMany(mappedBy = "order", cascade = CascadeType.MERGE, orphanRemoval = true)
	@JsonManagedReference(value="order_detail-order")
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Order() {
	}
	public Order(Long id, String code, String paymentMethod, EStatus status, User user) {
		this.id = id;
		this.code = code;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.user = user;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		System.out.println(user.getUserName());
		this.user = user;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public void setForeignKey(Order entity) {
		this.orderDetails = entity.orderDetails;
	}
}
