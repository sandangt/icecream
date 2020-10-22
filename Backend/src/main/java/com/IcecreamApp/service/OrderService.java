package com.IcecreamApp.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Order;
import com.IcecreamApp.repository.OrderRepository;

@Service
public class OrderService extends GeneralService<Order, OrderRepository> {

	public OrderService(OrderRepository repository) {
		super(repository);
	} 

	@Override
	public ResponseEntity<Order> update(long id, Order entity) {

		Optional<Order> currentEntityWrapper = this.repository.findById(id);

	    if (!currentEntityWrapper.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    entity.setForeignKey(currentEntityWrapper.get());
	    this.repository.save(entity);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}
}
