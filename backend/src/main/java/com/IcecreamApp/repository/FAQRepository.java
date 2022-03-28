package com.IcecreamApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {

	
	@Query(value="SELECT * FROM faq WHERE question LIKE CONCAT('%',:question,'%')", nativeQuery = true)
	List<FAQ> searchFAQByQuestion(@Param("question") String question);
}
