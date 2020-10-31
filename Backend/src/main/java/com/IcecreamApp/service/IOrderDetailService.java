package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.OrderDetailDTO;

public interface IOrderDetailService {

	OrderDetailDTO create(OrderDetailDTO orderDetailDTO);

	List<OrderDetailDTO> readAll();

	OrderDetailDTO readById(long id);

	OrderDetailDTO update(long id, OrderDetailDTO orderDetailDTO);

	void delete(long id);

}
