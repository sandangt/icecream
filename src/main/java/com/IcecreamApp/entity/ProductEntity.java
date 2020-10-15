package com.IcecreamApp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {

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
    private List<FeedbackEntity> feedbacks = new ArrayList<>();
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();
    
	@ManyToOne(fetch = FetchType.LAZY)
	private CategoryEntity category;

}
