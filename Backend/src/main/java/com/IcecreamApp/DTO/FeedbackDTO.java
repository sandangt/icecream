package com.IcecreamApp.DTO;

import java.util.Date;

public class FeedbackDTO extends BaseDTO {

	private String title;
	
	private String content;

	private UserDTO user;

	private ProductDTO product;
	
	public FeedbackDTO(long id, Date modifiedDate, String title, String content, UserDTO user, ProductDTO product) {
		super(id, modifiedDate);
		this.title = title;
		this.content = content;
		this.user = user;
		this.product = product;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}
}
