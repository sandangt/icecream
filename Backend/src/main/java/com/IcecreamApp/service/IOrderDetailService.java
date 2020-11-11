package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.entity.OrderDetail;

public interface IOrderDetailService {

	OrderDetail create(OrderDetailDTO orderDetailDTO);

	List<OrderDetailDTO> readAll();

	Optional<OrderDetailDTO> readById(long id);

	Optional<OrderDetail> update(long id, OrderDetailDTO orderDetailDTO);

	boolean delete(long id);

	OrderDetail createWithOrderCode(OrderDetailDTO orderDetailDTO);

}
