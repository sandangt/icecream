package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.converter.OrderDetailConverter;
import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.OrderDetailRepository;
import com.IcecreamApp.service.IOrderDetailService;

@Service
public class OrderDetailService implements IOrderDetailService {
	
	@Autowired
	private OrderDetailRepository repository;

	private String entityName = "Order detail";
	
	@Override
	public List<OrderDetailDTO> readAll() {
		
		List<OrderDetailDTO> orderDetails = new ArrayList<>();
		for (OrderDetail i : repository.findAll()) {
			orderDetails.add(OrderDetailConverter.toDTO(i));
		}
    	return orderDetails;
	}

	@Override
	public OrderDetailDTO readById(long id) {
		
		OrderDetail orderDetail = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		return OrderDetailConverter.toDTO(orderDetail);
	}

	@Override
	public OrderDetailDTO create(OrderDetailDTO orderDetailDTO) {
		
		repository.save(OrderDetailConverter.toEntity(orderDetailDTO));
		return orderDetailDTO;
	}

	@Override
	public OrderDetailDTO update(long id, OrderDetailDTO orderDetailDTO) {

		Optional<OrderDetail> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		OrderDetail orderDetail = OrderDetailConverter.toEntity(orderDetailDTO);
		this.repository.save(orderDetail);
		return orderDetailDTO;
	}

	@Override
	public void delete(long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		this.repository.deleteById(id);
	}

}
