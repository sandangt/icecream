package com.IcecreamApp.controller;

import java.util.List;
import java.util.Map;
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

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.entity.FAQ;
import com.IcecreamApp.service.IFAQService;

@CrossOrigin
@RestController
@RequestMapping("faq")
public class FAQController {
	
	@Autowired
	private IFAQService faqService;
	
    @GetMapping(value="/all")
    public ResponseEntity<List<FAQDTO>> getAllFAQs() {        
    	
    	return ResponseEntity.ok().body(faqService.readAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FAQDTO> getFAQById(@PathVariable("id") Long id) {
    	Optional<FAQDTO> currentEntityWrapper = faqService.readById(id);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(currentEntityWrapper.get());
    	}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<MessageResponseDTO> createFAQ(@RequestBody FAQDTO faqDTO) {
    	this.faqService.create(faqDTO);
    	return ResponseEntity.ok().body(new MessageResponseDTO("Created new FAQ successfully"));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> updateFAQ(@PathVariable("id") Long id, @RequestBody FAQDTO faqDTO) {
    	Optional<FAQ> currentEntityWrapper = this.faqService.update(id, faqDTO);
    	if (currentEntityWrapper.isPresent()) {
        	return ResponseEntity.ok().body(new MessageResponseDTO("Updated FAQ successfully!"));
    	}
		return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> deleteFAQ(@PathVariable("id") Long id) {
    	if (this.faqService.delete(id)) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("FAQ item has been deleted successfully!"));
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }
	
    @GetMapping
    public ResponseEntity<Map.Entry<Long,List<FAQDTO>>> getFAQByPage(@RequestParam int number, @RequestParam int size) {        
    	
    	return ResponseEntity.ok().body(faqService.readByPage(number, size));
    }
	
}
