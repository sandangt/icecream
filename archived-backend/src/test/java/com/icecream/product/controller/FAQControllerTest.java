package com.icecream.product.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.icecream.product.DTO.FAQDTO;
import com.icecream.product.DTO.MessageResponseDTO;
import com.icecream.product.converter.FAQConverter;
import com.icecream.product.service.impl.FAQService;

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

    @Test
    public void getFAQByIdTest() {
    	// Given
    	Mockito.when(faqService.readById(1L))
    	.thenReturn(Optional.ofNullable(new FAQDTO(1L, new Date(0), "What to?", "no idea")));
    	Mockito.when(faqService.readById(2L))
    	.thenReturn(Optional.ofNullable(new FAQDTO(2L, new Date(0), "How to?", "don't know")));
    	Mockito.when(faqService.readById(3L))
    	.thenReturn(Optional.ofNullable(new FAQDTO(3L, new Date(0), "Why to?", "no idea")));
    	Mockito.when(faqService.readById(4L))
    	.thenReturn(Optional.empty());
    	
    	// When
    	ResponseEntity<FAQDTO> result1 = faqController.getFAQById(1L);
    	ResponseEntity<FAQDTO> result2 = faqController.getFAQById(2L);
    	ResponseEntity<FAQDTO> result3 = faqController.getFAQById(3L);
    	ResponseEntity<FAQDTO> result4 = faqController.getFAQById(4L);
    	FAQDTO testResult1 = new FAQDTO(1L, new Date(0), "What to?", "no idea");
    	FAQDTO testResult2 = new FAQDTO(2L, new Date(0), "How to?", "don't know");
    	FAQDTO testResult3 = new FAQDTO(3L, new Date(0), "Why to?", "no idea");

    	// Then
    	assertEquals(result1.getStatusCode(), HttpStatus.OK);
    	assertEquals(result1.getBody().getAnswer(), testResult1.getAnswer());
    	assertEquals(result2.getBody().getQuestion(), testResult2.getQuestion());
    	assertEquals(result3.getBody().getId(), testResult3.getId());
    	assertEquals(result4.getStatusCode(), HttpStatus.NOT_FOUND);
        Mockito.verify(faqService, Mockito.times(4)).readById(Mockito.any(Long.class));
    }

    @Test
    public void createFAQTest() {
    	// Given

    	// When
    	ResponseEntity<MessageResponseDTO> result = faqController.createFAQ(new FAQDTO(1L, null, "What to?", "no idea"));
    	faqController.createFAQ(new FAQDTO(2L, null, "Why to?", "no idea"));
    	faqController.createFAQ(new FAQDTO(3L, null, "How to?", "no idea"));
    	
    	// Then
    	assertEquals(result.getStatusCode(), HttpStatus.OK);
    	Mockito.verify(faqService, Mockito.times(3)).create(Mockito.any(FAQDTO.class));
    }

    @Test
    public void updateFAQTest() {
		// Given
    	FAQDTO faq1 = new FAQDTO(1L, null, "What to?", "no idea");
    	FAQDTO faq2 = new FAQDTO(2L, null, "Why to?", "no idea");
    	FAQDTO faq3 =new FAQDTO(3L, null, "How to?", "no idea");
    	FAQDTO faq4 = new FAQDTO(4L, null, "How to?", "no idea");
    	
		Mockito.when(faqService.update(1L, faq1))
		.thenReturn(Optional.ofNullable(FAQConverter.toEntity(faq1)));
		Mockito.when(faqService.update(2L, faq2))
		.thenReturn(Optional.ofNullable(FAQConverter.toEntity(faq2)));
		Mockito.when(faqService.update(3L, faq3))
		.thenReturn(Optional.ofNullable(FAQConverter.toEntity(faq3)));
		Mockito.when(faqService.update(4L, faq4))
		.thenReturn(Optional.empty());
		
		// When
		ResponseEntity<MessageResponseDTO> result1 = faqController.updateFAQ(1L, faq1);
		ResponseEntity<MessageResponseDTO> result2 = faqController.updateFAQ(2L, faq2);
		ResponseEntity<MessageResponseDTO> result3 = faqController.updateFAQ(3L, faq3);
		ResponseEntity<MessageResponseDTO> result4 = faqController.updateFAQ(4L, faq4);
		
		// Then
		assertEquals(result1.getStatusCode(), HttpStatus.OK);
		assertEquals(result2.getStatusCode(), HttpStatus.OK);
		assertEquals(result3.getStatusCode(), HttpStatus.OK);
		assertEquals(result4.getStatusCode(), HttpStatus.NOT_FOUND);
		Mockito.verify(faqService, Mockito.times(4)).update(Mockito.any(Long.class), Mockito.any(FAQDTO.class));
    }

    @Test
    public void deleteFAQTest() {
    	// Given
    	Mockito.when(faqService.delete(1L)).thenReturn(true);
    	Mockito.when(faqService.delete(2L)).thenReturn(true);
    	Mockito.when(faqService.delete(3L)).thenReturn(false);

    	// When
    	ResponseEntity<MessageResponseDTO> result1 = faqController.deleteFAQ(1L);
    	ResponseEntity<MessageResponseDTO> result2 = faqController.deleteFAQ(2L);
    	ResponseEntity<MessageResponseDTO> result3 = faqController.deleteFAQ(3L);

    	// Then
    	assertEquals(result1.getStatusCode(), HttpStatus.OK);
    	assertEquals(result2.getStatusCode(), HttpStatus.OK);
    	assertEquals(result3.getStatusCode(), HttpStatus.NOT_FOUND);
    }
	
	
}
