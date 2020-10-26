package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.service.FeedbackService;

@RestController
@RequestMapping("feedbacks")
public class FeedbackController {

	private FeedbackService feedbackService;
	
	public FeedbackController(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}

	@GetMapping
	public List<Feedback> getAllFeedbacks() {        
		return feedbackService.readAll();
	}

	@GetMapping(value = "/{id}")
	public Feedback getFeedbackById(@PathVariable("id") Long id) {
		return feedbackService.readById(id);
	}

	@PostMapping
	public Feedback createFeedback(@RequestBody Feedback feedback) {
		return this.feedbackService.create(feedback);
	}

	@PutMapping(value = "/{id}")
	public Feedback updateFeedback(@PathVariable("id") Long id, @RequestBody Feedback feedback) {
		return this.feedbackService.update(id, feedback);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteFeedback(@PathVariable("id") Long id) {
		this.feedbackService.delete(id);
	}
}




