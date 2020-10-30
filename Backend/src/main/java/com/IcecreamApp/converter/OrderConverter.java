package com.IcecreamApp.converter;

import java.util.ArrayList;
import java.util.List;

import com.IcecreamApp.DTO.OrderDTO;
import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.entity.Order;
import com.IcecreamApp.entity.OrderDetail;
import com.IcecreamApp.entity.User;

public class OrderConverter {
	public OrderDTO toDTO(Order entity) {
		
		List<OrderDetailDTO> orderDetails = new ArrayList<>();
		for (OrderDetail i: entity.getOrderDetails()) {
			orderDetails.add(OrderDetailConverter.toDTO(i));
		}
		return new OrderDTO(entity.getId(), 
				entity.getModifiedDate(), 
				entity.getCode(), 
				entity.getPaymentMethod(),
				entity.getStatus(),
				UserConverter.toDTO(entity.getUser()), 
				orderDetails);
	}
	
	public Order toEntity(OrderDTO dto) {
		if (dto == null)
			return null;
		User user = UserConverter.toEntity(dto.getUser());
		return new Order(dto.getId(),
				dto.getCode(), 
				dto.getPaymentMethod(), 
				dto.getStatus(), 
				user);
	}
}