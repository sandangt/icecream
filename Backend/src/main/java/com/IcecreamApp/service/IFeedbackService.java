package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.FeedbackDTO;

public interface IFeedbackService {

	FeedbackDTO create(FeedbackDTO feedbackDTO);

	List<FeedbackDTO> readAll();

	FeedbackDTO readById(long id);

	FeedbackDTO update(long id, FeedbackDTO feedbackDTO);

	void delete(long id);
	
}