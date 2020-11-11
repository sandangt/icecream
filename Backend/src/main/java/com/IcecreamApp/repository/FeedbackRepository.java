package com.IcecreamApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	@Query(value="SELECT * FROM feedbacks WHERE product_id=:productId", nativeQuery = true)
	List<Feedback> findFeedbacksByProduct(@Param("productId") long productId);
}
