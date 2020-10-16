package com.IcecreamApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category extends Base {

	@Column(name="name", unique=true)
	private String name;
	
	/**
	 * Foreign key section
	 */
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> productEntities = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getProductEntities() {
		return productEntities;
	}

	public void setProductEntities(List<Product> productEntities) {
		this.productEntities = productEntities;
	}
	
}
