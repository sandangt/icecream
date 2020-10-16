package com.IcecreamApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderdetail")
public class OrderDetailEntity extends BaseEntity {
	
	@Column(name="code", columnDefinition="VARCHAR(250)")
	private String code;
	
	@Column(name="quantity", columnDefinition="INT")
	private Integer quantity;
	
	/**
	 * Foreign key section
	 */

	private OrderEntity order;
	
	private ProductEntity product;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id")
	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
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