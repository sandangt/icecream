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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.DTO.OrderDTO;
import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.DTO.PageDTO;
import com.IcecreamApp.entity.Order;
import com.IcecreamApp.service.IOrderService;

@CrossOrigin
@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAllOrders() {        
		return ResponseEntity.ok().body(orderService.readAll());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {		
    	Optional<OrderDTO> currentEntityWrapper = orderService.readById(id);
    	if (currentEntityWrapper.isPresent())
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<MessageResponseDTO> createOrder(@RequestBody OrderDTO orderDTO) {
		this.orderService.create(orderDTO);
		return ResponseEntity.ok().body(new MessageResponseDTO("Created new Order successfully"));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MessageResponseDTO> updateOrder(@PathVariable("id") Long id, @RequestBody OrderDTO orderDTO) {
    	Optional<Order> currentEntityWrapper = orderService.update(id, orderDTO);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated Order successfully!"));
    	}
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MessageResponseDTO> deleteOrder(@PathVariable("id") Long id) {
    	if (orderService.delete(id))
    		return ResponseEntity.ok().body(new MessageResponseDTO("Order item has been deleted successfully!"));
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
	}

	@GetMapping(params="code")
	public ResponseEntity<OrderDTO> getOrderByCode(@RequestParam("code") String code) {		
    	Optional<OrderDTO> currentEntityWrapper = orderService.readByCode(code);
    	if (currentEntityWrapper.isPresent())
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value="/{id}/order-details")
	public ResponseEntity<List<OrderDetailDTO>> getOrderDetailByOrder(@PathVariable("id") long id) {
		return ResponseEntity.ok().body(orderService.readOrderDetailsByOrder(id));
	}
	
    @GetMapping(params={"page","offset"})
    public ResponseEntity<PageDTO<OrderDTO>> getOrdersByPage(@RequestParam("page") int page, @RequestParam("offset") int offset) {
    	return ResponseEntity.ok().body(orderService.readByPage(page, offset));
    }
}
