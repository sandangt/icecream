package com.IcecreamApp.DTO;

import java.util.Date;

public class FeedbackDTO extends BaseDTO {

	private String title;
	
	private String content;
	
	/**
	 * Foreign keys
	 */
	private UserDTO user;

	private String productName;
//	@JsonIgnore
	private long productId;

	public FeedbackDTO(long id, Date modifiedDate, String title, String content, UserDTO user, String productName, long productId) {
		super(id, modifiedDate);
		this.title = title;
		this.content = content;
		this.user = user;
		this.productName = productName;
		this.productId = productId;
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

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
