package com.IcecreamApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="feedback")
public class FeedbackEntity extends BaseEntity {
	@Column(name="title", columnDefinition="VARCHAR(100)")
	private String title;
	
	@Column(name="content", columnDefinition="TEXT")
	private String content;
	
	/**
	 * Foreign key section
	 */
	
	private UserEntity user;
	
	
	private ProductEntity product;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id")
	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}
}
