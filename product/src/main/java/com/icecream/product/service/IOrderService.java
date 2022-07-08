package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.OrderDTO;
import com.icecream.product.DTO.OrderDetailDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.entity.Order;

public interface IOrderService {

	Order create(OrderDTO orderDTO);

	List<OrderDTO> readAll();

	Optional<OrderDTO> readById(long id);

	Optional<Order> update(long id, OrderDTO orderDTO);

	boolean delete(long id);
	
	Optional<OrderDTO> readByCode(String code);
	
	List<OrderDetailDTO> readOrderDetailsByOrder(long id);

	PageDTO<OrderDTO> readByPage(int pageNumber, int pageSize);
}
