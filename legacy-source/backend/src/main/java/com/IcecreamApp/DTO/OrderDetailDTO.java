package com.IcecreamApp.DTO;

import java.util.Date;

public class OrderDetailDTO extends BaseDTO {
	
	private int quantity;

	private ProductDTO product;
	
	private long orderId;
	private String orderCode;
	
	public OrderDetailDTO(long id, Date modifiedDate, int quantity, ProductDTO product, String orderCode) {
		super(id, modifiedDate);
		this.quantity = quantity;
		this.product = product;
		this.orderCode = orderCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
	public double getTotalPrice() {
		return (this.product.getPrice() * this.quantity);
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
}