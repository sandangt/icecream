package com.IcecreamApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="categories")
public class Category extends Base implements ForeignConnection<Category> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8919753937582267690L;
	
	@Column(name="name", unique=true)
	private String name;
	
	/**
	 * Foreign key section
	 */
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	@JsonManagedReference(value="product-category")
    private List<Product> products = new ArrayList<>();
	
	public Category() {
	}
	public Category(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public void setForeignKey(Category entity) {
		this.products = entity.products;
	}	
}
