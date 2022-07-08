package com.icecream.product.converter;

import com.icecream.product.DTO.OrderDetailDTO;
import com.icecream.product.entity.Order;
import com.icecream.product.entity.OrderDetail;

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
