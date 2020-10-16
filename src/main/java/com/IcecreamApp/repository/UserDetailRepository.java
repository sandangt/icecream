package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.UserDetailEntity;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetailEntity, Long> { 
    
}
