package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.OrderDetailEntity;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
