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

import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.service.IOrderDetailService;

@CrossOrigin
@RestController
@RequestMapping("order-details")
public class OrderDetailController {
	
	@Autowired
	private IOrderDetailService orderDetailService;
	
    @GetMapping
    public List<OrderDetailDTO> getAllOrderDetails() {        
    	return orderDetailService.readAll();
    }

    @GetMapping(value = "/{id}")
    public OrderDetailDTO getOrderDetailById(@PathVariable("id") Long id) {
    	return orderDetailService.readById(id);
    }

    @PostMapping
    public OrderDetailDTO createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
    	return this.orderDetailService.create(orderDetailDTO);
    }

    @PutMapping(value = "/{id}")
    public OrderDetailDTO updateOrderDetail(@PathVariable("id") Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
    	return this.orderDetailService.update(id, orderDetailDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteOrderDetail(@PathVariable("id") Long id) {
    	this.orderDetailService.delete(id);
    }

}



