package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.CartDTO;
import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.converter.OrderDetailConverter;
import com.IcecreamApp.entity.Order;
import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.repository.OrderDetailRepository;
import com.IcecreamApp.repository.OrderRepository;
import com.IcecreamApp.service.IOrderDetailService;

@Service
public class OrderDetailService implements IOrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private OrderRepository orderRepository;

	private String entityName = "Order detail";
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDetailService.class);
	
	@Override
	public List<OrderDetailDTO> readAll() {
    	return orderDetailRepository.findAll().stream().map(OrderDetailConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<OrderDetailDTO> readById(long id) {
		Optional<OrderDetail> currentEntityWrapper = orderDetailRepository.findById(id);
		if (currentEntityWrapper.isPresent())
			return Optional.ofNullable(OrderDetailConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public OrderDetail create(OrderDetailDTO orderDetailDTO) {
		return orderDetailRepository.save(OrderDetailConverter.toEntity(orderDetailDTO));
	}

	@Override
	public Optional<OrderDetail> update(long id, OrderDetailDTO orderDetailDTO) {

		Optional<OrderDetail> currentEntityWrapper = orderDetailRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return Optional.ofNullable(this.orderDetailRepository.save(OrderDetailConverter.toEntity(orderDetailDTO)));
	    }
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<OrderDetail> currentEntityWrapper = orderDetailRepository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.orderDetailRepository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}
	
	@Override
	public boolean createWithOrderCode(CartDTO cartDTO) {
		Optional<Order> currentOrderWrapper = orderRepository.findByCode(cartDTO.getOrderCode());
		if (currentOrderWrapper.isPresent()) {
			for (OrderDetailDTO i : cartDTO.getItemList()) {
				i.setOrderId(currentOrderWrapper.get().getId());
				orderDetailRepository.save(OrderDetailConverter.toEntity(i));
			}
			return true;
		}
		logger.error("order not found");
		return false;
	}

}
