package com.IcecreamApp.entity;

public interface ForeignConnection<Entitytype> {
	void setForeignKey(Entitytype entity);
}
