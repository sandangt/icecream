package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.DTO.PageDTO;
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
		Optional<Feedback> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent())
			return Optional.ofNullable(FeedbackConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
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
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<Feedback> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}

	@Override
	public PageDTO<FeedbackDTO> readByPage(int pageNumber, int pageSize) {
		Page<Feedback> pages = repository.findAll(PageRequest.of(--pageNumber, pageSize));
		Long totalEntities = repository.count();
		return new PageDTO<>(totalEntities, pages.getContent().stream().map(FeedbackConverter::toDTO).collect(Collectors.toList()));
	}
}