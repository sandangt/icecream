package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.FAQ;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {

}
