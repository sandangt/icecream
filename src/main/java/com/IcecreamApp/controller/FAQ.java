package com.IcecreamApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.entity.FAQEntity;
import com.IcecreamApp.repository.FAQRepository;

@CrossOrigin
@RestController
public class FAQ {

	@Autowired
	private FAQRepository faqRepository;
	
	@GetMapping(value = "/faq")
	public ResponseEntity<FAQEntity> createNew(@RequestBody NewDTO model) {
		return model;
	}
	
	@PutMapping(value = "/faq")
	public NewDTO updateNew(@RequestBody NewDTO model) {
		return model;
	}
	
	@DeleteMapping(value = "/faq/{id}")
	public void deleteNew(@RequestBody long[] ids) {
		
	}

}