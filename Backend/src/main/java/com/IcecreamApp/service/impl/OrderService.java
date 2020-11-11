package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.OrderDTO;
import com.IcecreamApp.converter.OrderConverter;
import com.IcecreamApp.entity.Order;
import com.IcecreamApp.repository.OrderRepository;
import com.IcecreamApp.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository repository;
	
	private String entityName = "Order";

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	
	@Override
	public List<OrderDTO> readAll() {    	
    	return repository.findAll().stream().map(OrderConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<OrderDTO> readById(long id) {
		Optional<Order> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) 
			return Optional.ofNullable(OrderConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public Order create(OrderDTO orderDTO) {
		return repository.save(OrderConverter.toEntity(orderDTO));
	}	
	
	@Override
	public Optional<Order> update(long id, OrderDTO orderDTO) {

		Optional<Order> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			Order order = OrderConverter.toEntity(orderDTO);
			order.setForeignKey(currentEntityWrapper.get());
			return Optional.ofNullable(this.repository.save(order));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}
	
	@Override
	public boolean delete(long id) {
		Optional<Order> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}

	@Override
	public Optional<OrderDTO> readByCode(String code) {
		Optional<Order> currentEntityWrapper = repository.findByCode(code);
		if (currentEntityWrapper.isPresent()) 
			return Optional.ofNullable(OrderConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s with code %s not found", entityName, code));
		return Optional.empty();
	}
}
