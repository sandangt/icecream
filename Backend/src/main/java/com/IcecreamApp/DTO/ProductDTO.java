package com.IcecreamApp.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.IcecreamApp.systemConstant.EStatus;

public class ProductDTO extends BaseDTO {

	private String name;
	
	private String description;

	private String image;
	
	private double price;
	
	private EStatus status;
    
    /**
     * Foreign key
     */
    private List<FeedbackDTO> feedbacks = new ArrayList<>();
    private long categoryId;
    private String categoryName;
    
	public ProductDTO(long id, Date modifiedDate, String name, String description, String image, double price,
			EStatus status, List<FeedbackDTO> feedbacks, String categoryName) {
		super(id, modifiedDate);
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.status = status;
		this.feedbacks = feedbacks;
		this.categoryName = categoryName; 
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public List<FeedbackDTO> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<FeedbackDTO> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
