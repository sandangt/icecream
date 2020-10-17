package com.IcecreamApp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="feedbacks")
public class Feedback extends Base implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -72177654282647916L;

	@Column(name="title", columnDefinition="VARCHAR(100)")
	private String title;
	
	@Column(name="content", columnDefinition="TEXT")
	private String content;
	
	/**
	 * Foreign key section
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	@JsonBackReference(value="feedback-user")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id", referencedColumnName = "id")
	@JsonBackReference(value="feedback-product")
	private Product product;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
