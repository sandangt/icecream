package com.IcecreamApp.DTO;

import java.util.Date;
import java.util.List;

import com.IcecreamApp.systemConstant.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderDTO extends BaseDTO {

	private String code;
	
	private String paymentMethod;
	
	private EStatus status;

	private UserDTO user;	

	private List<OrderDetailDTO> orderDetails;

	public OrderDTO(long id, Date modifiedDate, String code, String paymentMethod, EStatus status, UserDTO user,
			List<OrderDetailDTO> orderDetails) {
		super(id, modifiedDate);
		this.code = code;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.user = user;
		this.orderDetails = orderDetails;
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

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<OrderDetailDTO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	@JsonIgnore
	public double getTotalPrice() {
		double result = 0.0;
		for (OrderDetailDTO i : this.orderDetails) {
			result += i.getTotalPrice();
		}
		return result;
	}
}
