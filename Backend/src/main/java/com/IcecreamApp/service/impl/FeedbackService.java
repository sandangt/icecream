package com.IcecreamApp.service.impl;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.repository.FeedbackRepository;
import com.IcecreamApp.service.IFeedbackService;

@Service
public class FeedbackService extends GeneralService<Feedback, FeedbackRepository> implements IFeedbackService {

	public FeedbackService() {
		entityName = "feedback";
	}
	
}