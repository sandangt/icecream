package com.IcecreamApp.converter;

import com.IcecreamApp.DTO.OrderDetailDTO;
import com.IcecreamApp.entity.Order;
import com.IcecreamApp.entity.OrderDetail;

public class OrderDetailConverter {
	public static OrderDetailDTO toDTO(OrderDetail entity) {
		if (entity == null)
			return null;
		String orderCode = entity.getOrder() != null ? entity.getOrder().getCode() : null;
		return new OrderDetailDTO(entity.getId(), 
				entity.getModifiedDate(), 
				entity.getQuantity(), 
				ProductConverter.toDTO(entity.getProduct()), 
				orderCode);
	}
	
	public static OrderDetail toEntity(OrderDetailDTO dto) {
		if (dto == null) {
			return null;
		}
		Order order = new Order();
		order.setId(dto.getOrderId());
		
		return new OrderDetail(dto.getId(), dto.getQuantity(), order, ProductConverter.toEntity(dto.getProduct()));
	}
}
