package com.IcecreamApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.entity.Role;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Role> findByName(String name);
}
