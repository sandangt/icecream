package com.icecream.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.icecream.product.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	@Query(value="SELECT * FROM users WHERE username LIKE CONCAT('%',:username,'%')", nativeQuery = true)
	List<User> searchUsersByUsername(@Param("username") String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
