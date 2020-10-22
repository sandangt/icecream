package com.IcecreamApp.service;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.repository.OrderDetailRepository;

@Service
public class OrderDetailService extends GeneralService<OrderDetail, OrderDetailRepository> {

	public OrderDetailService(OrderDetailRepository repository) {
		super(repository);
	}
}
