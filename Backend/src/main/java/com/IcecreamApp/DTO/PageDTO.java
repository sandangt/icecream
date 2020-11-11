package com.IcecreamApp.DTO;

import java.util.List;

public class PageDTO<T> {
	private long totalItems;
	private List<T> itemList;
	
	
	public PageDTO(long totalItems, List<T> itemList) {
		this.totalItems = totalItems;
		this.itemList = itemList;
	}
	public long getTotalItems() {
		return totalItems;
	}
	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}
	public List<T> getItemList() {
		return itemList;
	}
	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}
}
