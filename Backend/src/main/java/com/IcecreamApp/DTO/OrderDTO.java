package com.IcecreamApp.DTO;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class OrderDTO extends BaseDTO {

	private long code;
	
	private String paymentMethod;
	
	private int status;

	private UserDTO user;	

	private Set<OrderDetailDTO> orderDetails;

	
	public OrderDTO(long id, Date modifiedDate, long code, String paymentMethod, int status, UserDTO user,
			Set<OrderDetailDTO> orderDetails) {
		super(id, modifiedDate);
		this.code = code;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.user = user;
		this.orderDetails = orderDetails;
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getStatus() {
		return status;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Set<OrderDetailDTO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetailDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
