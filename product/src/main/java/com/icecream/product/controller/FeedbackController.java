package com.icecream.product.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icecream.product.DTO.FeedbackDTO;
import com.icecream.product.DTO.MessageResponseDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.entity.Feedback;
import com.icecream.product.service.IFeedbackService;

@CrossOrigin
@RestController
@RequestMapping("feedbacks")
public class FeedbackController {

	@Autowired
	private IFeedbackService feedbackService;
	
	// Admin, User
	@GetMapping
	public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {        
		return ResponseEntity.ok().body(feedbackService.readAll());
	}

	// Admin, User
	@GetMapping(value = "/{id}")
	public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable("id") Long id) {
    	Optional<FeedbackDTO> currentEntityWrapper = feedbackService.readById(id);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
    	}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	// Admin, User
    @GetMapping(params={"page","offset"})
    public ResponseEntity<PageDTO<FeedbackDTO>> getOrdersByPage(@RequestParam("page") int page, @RequestParam("offset") int offset) {
    	return ResponseEntity.ok().body(feedbackService.readByPage(page, offset));
    }

	// Admin, User
	@PostMapping
	public ResponseEntity<MessageResponseDTO> createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
    	this.feedbackService.create(feedbackDTO);
    	return ResponseEntity.ok().body(new MessageResponseDTO("Created new Feedback successfully"));
	}

	// Admin, particular User
	@PutMapping(value = "/{id}")
	public ResponseEntity<MessageResponseDTO> updateFeedback(@PathVariable("id") Long id, @RequestBody FeedbackDTO feedbackDTO) {
    	Optional<Feedback> currentEntityWrapper = feedbackService.update(id, feedbackDTO);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated Feedback successfully"));
    	}
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
	}

	// Admin, particular User
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MessageResponseDTO> deleteFeedback(@PathVariable("id") Long id) {
    	if (feedbackService.delete(id)) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("Feedback item has been deleted successfully!"));
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
	}
}




