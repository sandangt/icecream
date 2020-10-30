package com.IcecreamApp.DTO;

import java.util.Date;

public class OrderDetailDTO extends BaseDTO {
	

	private int quantity;

	private ProductDTO product;
	
	public OrderDetailDTO(long id, Date modifiedDate, int quantity, ProductDTO product) {
		super(id, modifiedDate);
		this.quantity = quantity;
		this.product = product;
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
}