package com.IcecreamApp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.converter.FAQConverter;
import com.IcecreamApp.entity.FAQ;
import com.IcecreamApp.exception.ResourceNotFoundException;
import com.IcecreamApp.repository.FAQRepository;
import com.IcecreamApp.service.IFAQService;

@Service
public class FAQService implements IFAQService{
	
	@Autowired
	private FAQRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(FAQService.class);
	private String entityName = "FAQ";
	
	@Override
	public List<FAQDTO> readAll() {
		
		List<FAQDTO> faqs = new ArrayList<>();
		for (FAQ i : repository.findAll()) {
			faqs.add(FAQConverter.toDTO(i));
		}
    	return faqs;
	}

	@Override
	public Optional<FAQDTO> readById(long id) {
		
		FAQ faq = repository.findById(id).get();
		return Optional.ofNullable(FAQConverter.toDTO(faq));
	}

	@Override
	public FAQDTO create(FAQDTO faqDTO) {
		
		repository.save(FAQConverter.toEntity(faqDTO));
		return faqDTO;
	}

	@Override
	public Optional<FAQDTO> update(long id, FAQDTO faqDTO) {

		Optional<FAQ> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			logger.error("Resource not found");
			return Optional.empty();
		}
		FAQ faq = FAQConverter.toEntity(faqDTO);
		this.repository.save(faq);
		return Optional.ofNullable(faqDTO);
	}

	@Override
	public boolean delete(long id) {
		Optional<FAQ> currentEntityWrapper = repository.findById(id);
		if (!currentEntityWrapper.isPresent()) {
			logger.error("error: {}", new ResourceNotFoundException(entityName, id));
			return false;
		}
		this.repository.deleteById(id);
		return true;
	}

}
