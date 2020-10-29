package com.IcecreamApp.DTO;

import java.util.List;

import com.IcecreamApp.systemConstant.EStatus;

public class OrderDTO extends BaseDTO {

	private long code;
	
	private String paymentMethod;
	
	private EStatus status;

	private UserDTO user;	

	private List<OrderDetailDTO> orderDetails;

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
	
	public double getTotalPrice() {
		double result = 0.0;
		for (OrderDetailDTO i : this.orderDetails) {
			result += i.getTotalPrice();
		}
		return result;
	}
}
