package com.IcecreamApp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="order_details")
public class OrderDetail extends Base {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6829660858432189043L;

	@Column(name="quantity", columnDefinition="INT")
	private Integer quantity;
	
	/**
	 * Foreign key section
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="order_id", referencedColumnName = "id")
	@JsonBackReference(value="order_detail-order")
	private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id", referencedColumnName = "id")
	@JsonBackReference(value="order_detail-product")
	private Product product;

	public OrderDetail() {
		
	}
	public OrderDetail(Long id, Integer quantity, Order order, Product product) {
		this.id = id;
		this.quantity = quantity;
		this.order = order;
		this.product = product;
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}