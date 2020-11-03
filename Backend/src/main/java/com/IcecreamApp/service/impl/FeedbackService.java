package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.converter.FeedbackConverter;
import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.repository.FeedbackRepository;
import com.IcecreamApp.service.IFeedbackService;

@Service
public class FeedbackService implements IFeedbackService {
	
	@Autowired
	private FeedbackRepository repository;

	private String entityName = "Feedback";
	
	private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);
	
	@Override
	public List<FeedbackDTO> readAll() {    	
    	return repository.findAll().stream().map(FeedbackConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<FeedbackDTO> readById(long id) {
		Feedback feedback = repository.findById(id).get();
		return Optional.ofNullable(FeedbackConverter.toDTO(feedback));
	}

	@Override
	public Feedback create(FeedbackDTO feedbackDTO) {
		
		return repository.save(FeedbackConverter.toEntity(feedbackDTO));
	}

	@Override
	public Optional<Feedback> update(long id, FeedbackDTO feedbackDTO) {		
		Optional<Feedback> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return Optional.ofNullable(this.repository.save(FeedbackConverter.toEntity(feedbackDTO)));
		}
		logger.error(String.format("%s id %ld not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<Feedback> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %ld not found", entityName, id));
		return false;
	}
}