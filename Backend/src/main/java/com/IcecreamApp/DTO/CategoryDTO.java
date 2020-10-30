package com.IcecreamApp.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CategoryDTO extends BaseDTO {

	private String name;
	
    private List<ProductDTO> products = new ArrayList<>();
	
	public CategoryDTO(long id, Date modifiedDate, String name, List<ProductDTO> products) {
		super(id, modifiedDate);
		this.name = name;
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<ProductDTO> getProducts() {
		return this.products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}
}