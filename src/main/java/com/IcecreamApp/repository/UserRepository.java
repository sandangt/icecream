package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IcecreamApp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
