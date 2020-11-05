package com.Icecream.controller;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.controller.FAQController;
import com.IcecreamApp.service.impl.FAQService;

@RunWith(MockitoJUnitRunner.class)
public class FAQControllerTest {
	
	@Mock
	private FAQService faqService;
	@InjectMocks
	private FAQController faqController;
	
	@Test
	public void getAllFAQsTest() throws Exception {
		
		// Given
		Mockito.when(faqService.readAll()).thenReturn(Arrays.asList(
		new FAQDTO(1L, new Date(0), "What to?", "no idea"),
		new FAQDTO(2L, new Date(0), "How to?", "don't know"),
		new FAQDTO(3L, new Date(0), "Why to?", "no idea"),
		new FAQDTO(4L, new Date(0), "Where to?", "don't know, too!")
		));
		
		// When
        ResponseEntity<List<FAQDTO>> result = faqController.getAllFAQs();

        // Then
        assertEquals(result.getStatusCode(), HttpStatus.OK);
	}
	
	
	
}
