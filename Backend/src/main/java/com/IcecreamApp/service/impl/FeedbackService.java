package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.converter.FeedbackConverter;
import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.FeedbackRepository;
import com.IcecreamApp.service.IFeedbackService;

@Service
public class FeedbackService implements IFeedbackService {
	
	@Autowired
	private FeedbackRepository repository;

	private String entityName = "Feedback";
	
	@Override
	public List<FeedbackDTO> readAll() {
		
		List<FeedbackDTO> feedbacks = new ArrayList<>();
		for (Feedback i : repository.findAll()) {
			feedbacks.add(FeedbackConverter.toDTO(i));
		}
    	return feedbacks;
	}

	@Override
	public FeedbackDTO readById(long id) {
		
		Feedback feedback = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		return FeedbackConverter.toDTO(feedback);
	}

	@Override
	public FeedbackDTO create(FeedbackDTO feedbackDTO) {
		
		repository.save(FeedbackConverter.toEntity(feedbackDTO));
		return feedbackDTO;
	}

	@Override
	public FeedbackDTO update(long id, FeedbackDTO feedbackDTO) {

		Optional<Feedback> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			throw new ResourceNotFoundException(this.entityName, id);
	    }
		Feedback feedback = FeedbackConverter.toEntity(feedbackDTO);
		this.repository.save(feedback);
		return feedbackDTO;
	}

	@Override
	public void delete(long id) {
		repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(this.entityName, id));
		this.repository.deleteById(id);
	}
}