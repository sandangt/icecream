package com.icecream.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.icecream.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query(value="SELECT * FROM products WHERE name LIKE CONCAT('%',:productname,'%')", nativeQuery = true)
	List<Product> searchProductsByName(@Param("productname") String productname);

	@Query(value="SELECT * FROM products WHERE category_id=:categoryId", nativeQuery = true)
	List<Product> getProductsByCategory(@Param("categoryId") long categoryId);
	
	@Query(value="SELECT * FROM products WHERE category_id=:categoryId", nativeQuery = true)
	Page<Product> getPaginatedProductsByCategory(@Param("categoryId") long categoryId, Pageable pageable);
}
