package com.IcecreamApp.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Order;
import com.IcecreamApp.repository.OrderRepository;
import com.IcecreamApp.service.IOrderService;

@Service
public class OrderService extends GeneralService<Order, OrderRepository> implements IOrderService {

	@Override
	public ResponseEntity<Order> delete(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
