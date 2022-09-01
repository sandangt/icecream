package com.icecream.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icecream.product.entity.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	
	@Query(value="SELECT * FROM order_details WHERE order_id=:orderId", nativeQuery = true)
	List<OrderDetail> findOrderDetailsByOrder(@Param("orderId") long orderId);
}
