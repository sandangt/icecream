package com.icecream.product.DTO;

import java.util.List;

public class CartDTO {
	
	private String orderCode;
	
	private List<OrderDetailDTO> itemList;
	
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public List<OrderDetailDTO> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderDetailDTO> itemList) {
		this.itemList = itemList;
	} 
}
