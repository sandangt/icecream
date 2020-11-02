package com.IcecreamApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.DTO.MessageResponseDTO;
import com.IcecreamApp.service.IFAQService;

@RestController
@RequestMapping("faq")
public class FAQController {
	
	@Autowired
	private IFAQService faqService;
	
    @GetMapping
    public ResponseEntity<List<FAQDTO>> getAllCategories() {        
    	
    	return ResponseEntity.ok().body(faqService.readAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FAQDTO> getCategoryById(@PathVariable("id") Long id) {
    	Optional<FAQDTO> currentEntityWrapper = faqService.readById(id);
    	if (!currentEntityWrapper.isPresent()) {
    		return new ResponseEntity<>(currentEntityWrapper.get(), HttpStatus.NOT_FOUND);
    	}
    	return ResponseEntity.ok().body(currentEntityWrapper.get());
    }

    @PostMapping
    public FAQDTO createCategory(@RequestBody FAQDTO faqDTO) {
    	return this.faqService.create(faqDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FAQDTO> updateCategory(@PathVariable("id") Long id, @RequestBody FAQDTO faqDTO) {
    	Optional<FAQDTO> currentEntityWrapper = this.faqService.update(id, faqDTO);
    	if (!currentEntityWrapper.isPresent()) {
    		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    	}
    	return ResponseEntity.ok().body(currentEntityWrapper.get());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MessageResponseDTO> deleteCategory(@PathVariable("id") Long id) {
    	if (this.faqService.delete(id)) {
    		return ResponseEntity.ok().body(new MessageResponseDTO("Item has been deleted successfully!"));
    	}
    	return new ResponseEntity<>(new MessageResponseDTO("Item not found!"), HttpStatus.NOT_FOUND);
    }
	
}
