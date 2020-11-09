package com.IcecreamApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IcecreamApp.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value="SELECT * FROM products WHERE name LIKE CONCAT('%',:productname,'%')", nativeQuery = true)
	List<Product> searchProductsByName(@Param("productname") String productname);

}
