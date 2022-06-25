package com.IcecreamApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<Order> findByCode(String code);
	@Query(value="SELECT * FROM orders WHERE user_id=:userId", nativeQuery = true)
	List<Order> findByUserId(@Param("userId") long userId);
}
