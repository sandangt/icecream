package com.IcecreamApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.Feedback;
import com.IcecreamApp.service.IFeedbackService;

@RestController
@Transactional
public class FeedBackController {
	@Autowired
	private IFeedbackService feedbackService;

	@GetMapping(value = "/feedbacks")
	public ResponseEntity<List<Feedback>> getAllUser() {        
		return feedbackService.readAll();
	}

	@GetMapping(value = "/feedbacks/{id}")
	public ResponseEntity<Feedback> getUserById(@PathVariable("id") Long id) {
		return feedbackService.readById(id);
	}

	@PostMapping(value = "/feedbacks")
	public ResponseEntity<Feedback> createUser(@RequestBody Feedback feedback) {
		return this.feedbackService.create(feedback);
	}

	@PutMapping(value = "/feedbacks/{id}")
	public ResponseEntity<Feedback> updateUser(@PathVariable("id") Long id, @RequestBody Feedback feedback) {
		return this.feedbackService.update(id, feedback);
	}

	@DeleteMapping(value = "/feedbacks/{id}")
	public ResponseEntity<Feedback> deleteUser(@PathVariable("id") Long id) {
		return this.feedbackService.delete(id);
	}
}




