package com.IcecreamApp.entity;

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

@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

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
    
    private List<FeedbackEntity> feedbacks = new ArrayList<>();
    
    
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();

	private CategoryEntity category;
    
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
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<FeedbackEntity> getFeedbacks() {
		return feedbacks;
	}

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	public List<OrderDetailEntity> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailEntity> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(
	        name = "categoryName",
	        referencedColumnName = "name"
	    )
	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}

}
