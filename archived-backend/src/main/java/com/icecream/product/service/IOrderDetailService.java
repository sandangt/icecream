package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.CartDTO;
import com.icecream.product.DTO.OrderDetailDTO;
import com.icecream.product.entity.OrderDetail;

public interface IOrderDetailService {

	OrderDetail create(OrderDetailDTO orderDetailDTO);

	List<OrderDetailDTO> readAll();

	Optional<OrderDetailDTO> readById(long id);

	Optional<OrderDetail> update(long id, OrderDetailDTO orderDetailDTO);

	boolean delete(long id);

	boolean createWithOrderCode(CartDTO cartDTO);

}
