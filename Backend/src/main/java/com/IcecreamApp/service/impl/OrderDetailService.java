package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.converter.OrderDetailConverter;
import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.repository.OrderDetailRepository;
import com.IcecreamApp.service.IOrderDetailService;

@Service
public class OrderDetailService implements IOrderDetailService {
	
	@Autowired
	private OrderDetailRepository repository;

	private String entityName = "Order detail";
	
	private static final Logger logger = LoggerFactory.getLogger(OrderDetailService.class);
	
	@Override
	public List<OrderDetailDTO> readAll() {
    	return repository.findAll().stream().map(OrderDetailConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<OrderDetailDTO> readById(long id) {
		Optional<OrderDetail> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent())
			return Optional.ofNullable(OrderDetailConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %ld not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public OrderDetail create(OrderDetailDTO orderDetailDTO) {
		return repository.save(OrderDetailConverter.toEntity(orderDetailDTO));
	}

	@Override
	public Optional<OrderDetail> update(long id, OrderDetailDTO orderDetailDTO) {

		Optional<OrderDetail> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return Optional.ofNullable(this.repository.save(OrderDetailConverter.toEntity(orderDetailDTO)));
	    }
		logger.error(String.format("%s id %ld not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<OrderDetail> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %ld not found", entityName, id));
		return false;
	}

}
