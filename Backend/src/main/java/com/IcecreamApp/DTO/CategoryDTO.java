package com.IcecreamApp.DTO;

import java.util.Date;
import java.util.Set;

public class CategoryDTO extends BaseDTO {

	private String name;
	
	private Set<ProductDTO> products;
	
	public CategoryDTO(long id, Date modifiedDate, String name, Set<ProductDTO> products) {
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

	public Set<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductDTO> products) {
		this.products = products;
	}
}
