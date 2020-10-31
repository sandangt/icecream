package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.OrderDTO;

public interface IOrderService {

	OrderDTO create(OrderDTO orderDTO);

	List<OrderDTO> readAll();

	OrderDTO readById(long id);

	OrderDTO update(long id, OrderDTO orderDTO);

	void delete(long id);

}
