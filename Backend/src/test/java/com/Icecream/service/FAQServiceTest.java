package com.Icecream.service;

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
		// Then
		assertEquals("What to?", entity1.get().getQuestion());
		assertEquals("no idea", entity2.get().getAnswer());
		assertEquals(3L, entity3.get().getId());
		Mockito.verify(faqRepository, Mockito.times(3)).findById(Mockito.any(Long.class));
		
	}
	@Test
	public void createTest() {
		// Given
		
		// When
		faqService.create(new FAQDTO(1L, new Date(0), "What to?", "no idea"));
		faqService.create(new FAQDTO(2L, new Date(0), "Why to?", "no idea"));
		faqService.create(new FAQDTO(3L, new Date(0), "How to?", "no idea"));
		// Then
		Mockito.verify(faqRepository, Mockito.times(3)).save(Mockito.any(FAQ.class));
	}
//	@Test
//	public void updateTest() {
//		throw new NotYetImplementedException();
//	}
//	@Test
//	public void deleteTest() {
//		throw new NotYetImplementedException();
//	}

//	Optional<FAQDTO> readById(long id);
//
//	FAQ create(FAQDTO faqDTO);
//
//	Optional<FAQ> update(long id, FAQDTO faqDTO);
//
//	boolean delete(long id);
}
