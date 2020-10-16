package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
