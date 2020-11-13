package com.IcecreamApp.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.entity.FAQ;
import com.IcecreamApp.repository.FAQRepository;
import com.IcecreamApp.service.impl.FAQService;

@RunWith(MockitoJUnitRunner.class)
public class FAQServiceTest {
	
	@Mock
	private FAQRepository faqRepository;
	@InjectMocks
	private FAQService faqService;
	
	@Test
	public void readAllTest() {
		// Given
		Mockito.when(faqRepository.findAll()).thenReturn(Arrays.asList(
				new FAQ(1L, "What to?", "no idea"),
				new FAQ(2L, "Why to?", "no idea"),
				new FAQ(3L, "How to?", "no idea")
		));
		// When
		List<FAQDTO> faqs = faqService.readAll();
		// Then
		assertEquals("What to?", faqs.get(0).getQuestion());
		assertEquals("Why to?", faqs.get(1).getQuestion());
		assertEquals("How to?", faqs.get(2).getQuestion());
		assertEquals(1L, faqs.get(0).getId());
		assertEquals(2L, faqs.get(1).getId());
		assertEquals(3L, faqs.get(2).getId());
		Mockito.verify(faqRepository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void readByIdTest() {
		// Given
		Mockito.when(faqRepository.findById(1L)).thenReturn(
				Optional.ofNullable(new FAQ(1L, "What to?", "no idea")));
		Mockito.when(faqRepository.findById(2L)).thenReturn(
				Optional.ofNullable(new FAQ(2L, "Why to?", "no idea")));
		Mockito.when(faqRepository.findById(3L)).thenReturn(
				Optional.ofNullable(new FAQ(3L, "How to?", "no idea")));
		// When
		Optional<FAQDTO> entity1 = faqService.readById(1L);
		Optional<FAQDTO> entity2 = faqService.readById(2L);
		Optional<FAQDTO> entity3 = faqService.readById(3L);
		Optional<FAQDTO> entity4 = faqService.readById(4L);
		// Then
		assertEquals("What to?", entity1.get().getQuestion());
		assertEquals("no idea", entity2.get().getAnswer());
		assertEquals(3L, entity3.get().getId());
		assertEquals(Optional.empty(), entity4);
		Mockito.verify(faqRepository, Mockito.times(4)).findById(Mockito.any(Long.class));
	}
	
	@Test
	public void createTest() {
		// Given
		FAQDTO faqdto1 = new FAQDTO(1L, new Date(0), "What to?", "no idea");
		FAQDTO faqdto2 = new FAQDTO(2L, new Date(0), "Why to?", "no idea");
		FAQDTO faqdto3 = new FAQDTO(3L, new Date(0), "How to?", "no idea");
//		FAQ faq1 = FAQConverter.toEntity(faqdto1);
//		FAQ faq2 = FAQConverter.toEntity(faqdto2);
//		FAQ faq3 = FAQConverter.toEntity(faqdto3);
//		Mockito.when(faqRepository.save(faq1)).thenReturn(faq1);
//		Mockito.when(faqRepository.save(faq2)).thenReturn(faq2);
//		Mockito.when(faqRepository.save(faq3)).thenReturn(faq3);
		
		// When
		faqService.create(faqdto1);
		faqService.create(faqdto2);
		faqService.create(faqdto3);
		// Then
//		assertEquals(faq1.getAnswer(), faqTest1.getAnswer());
//		assertEquals(faq2.getId(), faqTest2.getId());
//		assertEquals(faq3.getQuestion(), faqTest3.getQuestion());
		Mockito.verify(faqRepository, Mockito.times(3)).save(Mockito.any(FAQ.class));
	}
	@Test
	public void updateTest() {
		// Given
		Mockito.when(faqRepository.findById(1L)).thenReturn(
				Optional.ofNullable(new FAQ(1L, "What", "don't know")));
		Mockito.when(faqRepository.findById(2L)).thenReturn(
				Optional.ofNullable(new FAQ(2L, "Why", "don't know")));
		Mockito.when(faqRepository.findById(3L)).thenReturn(
				Optional.ofNullable(new FAQ(3L, "How", "don't know")));
		Mockito.when(faqRepository.findById(4L)).thenReturn(
				Optional.empty());
		
		// When
		faqService.update(1L, new FAQDTO(1L, null, "What to?", "no idea"));
		faqService.update(2L, new FAQDTO(2L, null, "Why to?", "no idea"));
		faqService.update(3L, new FAQDTO(3L, null, "How to?", "no idea"));
		faqService.update(4L, new FAQDTO(4L, null, "Where to?", "no idea"));
		
		// Then
		Mockito.verify(faqRepository, Mockito.times(3)).save(Mockito.any(FAQ.class));
	}
	@Test
	public void deleteTest() {
		// Given
		Mockito.when(faqRepository.findById(1L)).thenReturn(
				Optional.ofNullable(new FAQ(1L, "What", "don't know")));
		Mockito.when(faqRepository.findById(2L)).thenReturn(
				Optional.ofNullable(new FAQ(2L, "Why", "don't know")));
		Mockito.when(faqRepository.findById(3L)).thenReturn(
				Optional.ofNullable(new FAQ(3L, "How", "don't know")));
		Mockito.when(faqRepository.findById(4L)).thenReturn(
				Optional.empty());
		
		// When
		boolean result1 = faqService.delete(1L);
		faqService.delete(2L);
		faqService.delete(3L);
		boolean result4 = faqService.delete(4L);
		
		// Then
		assertEquals(result1, true);
		assertEquals(result4, false);
		Mockito.verify(faqRepository, Mockito.times(3)).deleteById(Mockito.any(Long.class));
	}
}