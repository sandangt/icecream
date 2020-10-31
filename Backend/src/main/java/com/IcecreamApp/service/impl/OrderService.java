package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.OrderDTO;
import com.IcecreamApp.converter.OrderConverter;
import com.IcecreamApp.entity.Order;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.OrderRepository;
import com.IcecreamApp.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository repository;
	private String entityName = "Order";
	Logger log = LoggerFactory.getLogger(CategoryService.class);
	
	@Override
	public List<OrderDTO> readAll() {
		
		List<OrderDTO> orders = new ArrayList<>();
		
    	for (Order i : repository.findAll()) {
    		orders.add(OrderConverter.toDTO(i));
    	}
    	return orders;
	}

	@Override
	public OrderDTO readById(long id) {
		Order order = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
    	return OrderConverter.toDTO(order);
	}

	@Override
	public OrderDTO create(OrderDTO orderDTO) {
		repository.save(OrderConverter.toEntity(orderDTO));
		return orderDTO;
	}	
	
	@Override
	public OrderDTO update(long id, OrderDTO orderDTO) {

		Optional<Order> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		Order order = OrderConverter.toEntity(orderDTO);
		order.setForeignKey(currentEntityWrapper.get());
		this.repository.save(order);
		return orderDTO;
	}
	
	@Override
	public void delete(long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		this.repository.deleteById(id);
	}
}
