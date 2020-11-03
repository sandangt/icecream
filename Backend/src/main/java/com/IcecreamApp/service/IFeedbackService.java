package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.entity.Feedback;

public interface IFeedbackService {

	List<FeedbackDTO> readAll();

	Optional<FeedbackDTO> readById(long id);
	
	Feedback create(FeedbackDTO feedbackDTO);

	Optional<Feedback> update(long id, FeedbackDTO feedbackDTO);

	boolean delete(long id);
	
}