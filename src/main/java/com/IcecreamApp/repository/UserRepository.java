package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IcecreamApp.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
