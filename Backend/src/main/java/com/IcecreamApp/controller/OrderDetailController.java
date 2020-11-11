package com.IcecreamApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.service.IOrderDetailService;

@CrossOrigin
@RestController
@RequestMapping("order-details")
public class OrderDetailController {
	
	@Autowired
	private IOrderDetailService orderDetailService;
	
    @GetMapping
    public ResponseEntity<List<OrderDetailDTO>> getAllOrderDetails() {        
    	return ResponseEntity.ok().body(orderDetailService.readAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDetailDTO> getOrderDetailById(@PathVariable("id") Long id) {
    	Optional<OrderDetailDTO> currentEntityWrapper = orderDetailService.readById(id);
    	if (currentEntityWrapper.isPresent())
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
    	orderDetailService.create(orderDetailDTO);
    	return ResponseEntity.ok().body(new MessageResponseDTO("Created new Order detail successfully"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> updateOrderDetail(@PathVariable("id") Long id, @RequestBody OrderDetailDTO orderDetailDTO) {
    	Optional<OrderDetail> currentEntityWrapper = orderDetailService.update(id, orderDetailDTO);
    	if (currentEntityWrapper.isPresent())
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated Order detail successfully!"));
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> deleteOrderDetail(@PathVariable("id") Long id) {
    	if (orderDetailService.delete(id)) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("Order detail item has been deleted successfully!"));
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(value="/code")
    public ResponseEntity<MessageResponseDTO> createOrderDetailWithOrderCode(@RequestBody OrderDetailDTO orderDetailDTO) {
    	if (orderDetailService.createWithOrderCode(orderDetailDTO) != null)
    		return ResponseEntity.ok().body(new MessageResponseDTO("Created new Order detail successfully"));
		return new ResponseEntity<>(new MessageResponseDTO("Order not found!"), HttpStatus.NOT_FOUND);
    }

}



