package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IcecreamApp.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
