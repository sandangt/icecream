package com.IcecreamApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductEntity product;
}
