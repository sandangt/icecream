package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Order;
import com.IcecreamApp.service.IOrderService;

@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@GetMapping
	public List<Order> getAllOrders() {        
		return orderService.readAll();
	}

	@GetMapping(value = "/{id}")
	public Order getOrderById(@PathVariable("id") Long id) {
		return orderService.readById(id);
	}

	@PostMapping
	public Order createOrder(@RequestBody Order order) {
		return this.orderService.create(order);
	}

	@PutMapping(value = "/{id}")
	public Order updateOrder(@PathVariable("id") Long id, @RequestBody Order order) {
		return this.orderService.update(id, order);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteOrder(@PathVariable("id") Long id) {
		this.orderService.delete(id);
	}
}
