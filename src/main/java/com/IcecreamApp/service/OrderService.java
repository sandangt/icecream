package com.IcecreamApp.service;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Order;
import com.IcecreamApp.repository.OrderRepository;

@Service
public class OrderService extends GeneralService<Order, OrderRepository> {

	public OrderService(OrderRepository repository) {
		super(repository);
	} 
}
