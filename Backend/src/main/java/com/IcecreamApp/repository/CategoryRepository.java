package com.IcecreamApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.entity.Role;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Role> findByName(String name);
	@Query(value="SELECT * FROM categories WHERE name LIKE CONCAT('%',:categoryname,'%')", nativeQuery = true)
	List<Category> searchCategoriesByName(@Param("categoryname") String categoryname);
}
