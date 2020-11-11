package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.DTO.OrderDTO;
import com.IcecreamApp.entity.Order;

public interface IOrderService {

	Order create(OrderDTO orderDTO);

	List<OrderDTO> readAll();

	Optional<OrderDTO> readById(long id);

	Optional<Order> update(long id, OrderDTO orderDTO);

	boolean delete(long id);
	
	Optional<OrderDTO> readByCode(String code);

}
