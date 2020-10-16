package com.IcecreamApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IcecreamApp.entity.FeedbackEntity;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
}
