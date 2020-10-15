package com.IcecreamApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderdetail")
public class OrderDetailEntity {
	
	@Column(name="code", columnDefinition="VARCHAR(250)")
	private String code;
	
	@Column(name="quantity", columnDefinition="INT")
	private Integer quantity;
	
	/**
	 * Foreign key section
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	private OrderEntity order;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ProductEntity product;
	
}
