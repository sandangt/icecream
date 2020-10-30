package com.IcecreamApp.service.impl;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.repository.OrderDetailRepository;
import com.IcecreamApp.service.IOrderDetailService;

@Service
public class OrderDetailService extends GeneralService<OrderDetail, OrderDetailRepository> implements IOrderDetailService {

	public OrderDetailService() {
		entityName = "order detail";
	}
}
