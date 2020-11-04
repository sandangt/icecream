package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.OrderDTO;
import com.IcecreamApp.service.IOrderService;

@CrossOrigin
@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@GetMapping
	public List<OrderDTO> getAllOrders() {        
		return orderService.readAll();
	}

	@GetMapping(value = "/{id}")
	public OrderDTO getOrderById(@PathVariable("id") Long id) {
		return orderService.readById(id);
	}

	@PostMapping
	public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
		return this.orderService.create(orderDTO);
	}

	@PutMapping(value = "/{id}")
	public OrderDTO updateOrder(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO) {
		return this.orderService.update(id, orderDTO);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteOrder(@PathVariable("id") Long id) {
		this.orderService.delete(id);
	}
}
