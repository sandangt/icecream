package com.icecream.product.entity;

public interface ForeignConnection<Entitytype> {
	void setForeignKey(Entitytype entity);
}
