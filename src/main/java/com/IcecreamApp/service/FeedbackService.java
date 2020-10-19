package com.IcecreamApp.service;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.repository.FeedbackRepository;

@Service
public class FeedbackService extends GeneralService<Feedback, FeedbackRepository> {

	public FeedbackService(FeedbackRepository repository) {
		super(repository);
	}
	
}