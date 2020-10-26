package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.service.OrderDetailService;


@RestController
@RequestMapping("order-details")
public class OrderDetailController {
	
	private OrderDetailService orderDetailService;
	
	public OrderDetailController(OrderDetailService orderDetailService) {
		this.orderDetailService = orderDetailService;
	}

    @GetMapping
    public List<OrderDetail> getAllOrderDetails() {        
    	return orderDetailService.readAll();
    }

    @GetMapping(value = "/{id}")
    public OrderDetail getOrderDetailById(@PathVariable("id") Long id) {
    	return orderDetailService.readById(id);
    }

    @PostMapping
    public OrderDetail createOrderDetail(@RequestBody OrderDetail orderDetail) {
    	return this.orderDetailService.create(orderDetail);
    }

    @PutMapping(value = "/{id}")
    public OrderDetail updateOrderDetail(@PathVariable("id") Long id, @RequestBody OrderDetail orderDetail) {
    	return this.orderDetailService.update(id, orderDetail);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrderDetail(@PathVariable("id") Long id) {
    	this.orderDetailService.delete(id);
    }

}



