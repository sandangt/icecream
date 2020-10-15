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
@Table(name="order")
public class OrderEntity extends BaseEntity {
	@Column(name="paymentmethod", columnDefinition="VARCHAR(100)")
	private String paymentMethod;
	
	@Column(name="status", columnDefinition="TINYINT")
	private Integer status;
	
	/**
	 * Foreign key section
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;	
	
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailEntity> orderDetails = new ArrayList<>();
}
