package com.IcecreamApp.DTO;

import java.util.Date;

public class OrderDetailDTO extends BaseDTO {
	

	private String code;
	
	private int quantity;

	private OrderDTO order;

	private ProductDTO product;

	
	public OrderDetailDTO(long id, Date modifiedDate, String code, int quantity, OrderDTO order, ProductDTO product) {
		super(id, modifiedDate);
		this.code = code;
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
}