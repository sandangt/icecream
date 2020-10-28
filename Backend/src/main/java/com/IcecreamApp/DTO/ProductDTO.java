package com.IcecreamApp.DTO;

import java.util.Date;
import java.util.Set;

public class ProductDTO extends BaseDTO {

	private String name;
	
	private String description;

	private String image;
	
	private double price;
	
	private int status;

    private Set<FeedbackDTO> feedbacks;
    
    private Set<OrderDetailDTO> orderDetails;

	private String category_name;
	
	
	
	public ProductDTO(long id, Date modifiedDate, String name, String description, String image, double price,
			int status, Set<FeedbackDTO> feedbacks, Set<OrderDetailDTO> orderDetails, String category_name) {
		super(id, modifiedDate);
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.status = status;
		this.feedbacks = feedbacks;
		this.orderDetails = orderDetails;
		this.category_name = category_name;
	}

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Set<FeedbackDTO> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Set<FeedbackDTO> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Set<OrderDetailDTO> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(Set<OrderDetailDTO> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getCategory() {
		return category_name;
	}

	public void setCategory(String category_name) {
		this.category_name = category_name;
	}
	
}
