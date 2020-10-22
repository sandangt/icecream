package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IcecreamApp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
