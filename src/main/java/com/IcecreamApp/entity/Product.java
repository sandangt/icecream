package com.IcecreamApp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "products")
public class Product extends Base implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -321247931329528117L;

	@Column(name = "name", columnDefinition="VARCHAR(100)")
	private String name;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;

	@Column(name = "image")
	private String image;
	
	@Column(name="price", columnDefinition="FLOAT")
	private Double price;
	
	@Column(name="status", columnDefinition="TINYINT")
	private Integer status;
	
	/**
	 * Foreign key section
	 */

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="feedback-product")
    private List<Feedback> feedbacks = new ArrayList<>();
    
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference(value="order_detail-product")
    private List<OrderDetail> orderDetails = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_name", referencedColumnName = "name")
	@JsonBackReference(value="product-category")
	private Category category;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
