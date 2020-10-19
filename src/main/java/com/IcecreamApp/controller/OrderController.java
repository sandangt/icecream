package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Order;
import com.IcecreamApp.service.OrderService;

@RestController
public class OrderController {

	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(value = "/orders")
	public ResponseEntity<List<Order>> getAllOrders() {        
		return orderService.readAll();
	}

	@GetMapping(value = "/orders/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id) {
		return orderService.readById(id);
	}

	@PostMapping(value = "/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		return this.orderService.create(order);
	}

	@PutMapping(value = "/orders/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
		return this.orderService.update(id, order);
	}

	@DeleteMapping(value = "/orders/{id}")
	public ResponseEntity<Order> deleteOrder(@PathVariable("id") Long id) {
		return this.orderService.delete(id);
	}
}
