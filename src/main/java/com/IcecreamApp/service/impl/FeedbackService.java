package com.IcecreamApp.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.repository.FeedbackRepository;
import com.IcecreamApp.service.IFeedbackService;

@Service
public class FeedbackService extends GeneralService<Feedback, FeedbackRepository> implements IFeedbackService {

	@Override
	public ResponseEntity<Feedback> delete(long id) {

    	Optional<Feedback> currentFeedbackWrapper = repository.findById(id);
        if (!currentFeedbackWrapper.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Feedback currentFeedback = currentFeedbackWrapper.get();
        repository.delete(currentFeedback);
        return new ResponseEntity<>(currentFeedback, HttpStatus.OK);
	}
}