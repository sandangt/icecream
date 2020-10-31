package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.FeedbackDTO;
import com.IcecreamApp.service.IFeedbackService;

@RestController
@RequestMapping("feedbacks")
public class FeedbackController {

	@Autowired
	private IFeedbackService feedbackService;
	
	@GetMapping
	public List<FeedbackDTO> getAllFeedbacks() {        
		return feedbackService.readAll();
	}

	@GetMapping(value = "/{id}")
	public FeedbackDTO getFeedbackById(@PathVariable("id") Long id) {
		return feedbackService.readById(id);
	}

	@PostMapping
	public FeedbackDTO createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		return this.feedbackService.create(feedbackDTO);
	}

	@PutMapping(value = "/{id}")
	public FeedbackDTO updateFeedback(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {
		return this.feedbackService.update(id, feedbackDTO);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteFeedback(@PathVariable("id") Long id) {
		this.feedbackService.delete(id);
	}
}




