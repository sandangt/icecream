package com.icecream.product.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.icecream.product.converter.OrderConverter;
import com.icecream.product.converter.OrderDetailConverter;
import com.icecream.product.repository.OrderDetailRepository;
import com.icecream.product.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.icecream.product.DTO.OrderDTO;
import com.icecream.product.DTO.OrderDetailDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.entity.Order;
import com.icecream.product.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	private String entityName = "Order";

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	
	@Override
	public List<OrderDTO> readAll() {    	
    	return orderRepository.findAll().stream().map(OrderConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<OrderDTO> readById(long id) {
		Optional<Order> currentEntityWrapper = orderRepository.findById(id);
		if (currentEntityWrapper.isPresent()) 
			return Optional.ofNullable(OrderConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public Order create(OrderDTO orderDTO) {
		return orderRepository.save(OrderConverter.toEntity(orderDTO));
	}	
	
	@Override
	public Optional<Order> update(long id, OrderDTO orderDTO) {

		Optional<Order> currentEntityWrapper = orderRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			Order order = OrderConverter.toEntity(orderDTO);
			order.setForeignKey(currentEntityWrapper.get());
			return Optional.ofNullable(this.orderRepository.save(order));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}
	
	@Override
	public boolean delete(long id) {
		Optional<Order> currentEntityWrapper = orderRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.orderRepository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}

	@Override
	public Optional<OrderDTO> readByCode(String code) {
		Optional<Order> currentEntityWrapper = orderRepository.findByCode(code);
		if (currentEntityWrapper.isPresent()) 
			return Optional.ofNullable(OrderConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s with code %s not found", entityName, code));
		return Optional.empty();
	}

	@Override
	public List<OrderDetailDTO> readOrderDetailsByOrder(long id) {
		Optional<Order> currentEntityWrapper = orderRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return orderDetailRepository.findOrderDetailsByOrder(id).stream().map(OrderDetailConverter::toDTO).collect(Collectors.toList());
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return new ArrayList<>();
	}

	@Override
	public PageDTO<OrderDTO> readByPage(int pageNumber, int pageSize) {
		Page<Order> pages = orderRepository.findAll(PageRequest.of(--pageNumber, pageSize));
		Long totalEntities = orderRepository.count();
		return new PageDTO<>(totalEntities, pages.getContent().stream().map(OrderConverter::toDTO).collect(Collectors.toList()));
	}
}
