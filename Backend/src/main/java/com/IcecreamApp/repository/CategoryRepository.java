package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
