package com.icecream.product.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.icecream.product.repository.FAQRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.icecream.product.DTO.FAQDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.converter.FAQConverter;
import com.icecream.product.entity.FAQ;
import com.icecream.product.service.IFAQService;

@Service
public class FAQService implements IFAQService{
	
	@Autowired
	private FAQRepository repository;
	
	private static final Logger logger = LoggerFactory.getLogger(FAQService.class);
	
	private String entityName = "FAQ";
	
	@Override
	public List<FAQDTO> readAll() {
		
		return repository.findAll().stream().map(FAQConverter::toDTO).collect(Collectors.toList());
	}

	@Override
	public Optional<FAQDTO> readById(long id) {
		Optional<FAQ> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent())
			return Optional.ofNullable(FAQConverter.toDTO(currentEntityWrapper.get()));
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public FAQ create(FAQDTO faqDTO) {
		return repository.save(FAQConverter.toEntity(faqDTO));
	}

	@Override
	public Optional<FAQ> update(long id, FAQDTO faqDTO) {
		Optional<FAQ> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			return Optional.ofNullable(this.repository.save(FAQConverter.toEntity(faqDTO)));
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return Optional.empty();
	}

	@Override
	public boolean delete(long id) {
		Optional<FAQ> currentEntityWrapper = repository.findById(id);
		if (currentEntityWrapper.isPresent()) {
			this.repository.deleteById(id);
			return true;
		}
		logger.error(String.format("%s id %d not found", entityName, id));
		return false;
	}

	@Override
	public PageDTO<FAQDTO> readByPage(int pageNumber, int pageSize) {
		Page<FAQ> pages = repository.findAll(PageRequest.of(--pageNumber, pageSize));
		Long totalEntities = repository.count();
		return new PageDTO<FAQDTO>(totalEntities, pages.getContent().stream().map(FAQConverter::toDTO).collect(Collectors.toList()));
	}

	@Override
	public List<FAQDTO> searchFAQByQuestion(String question) {
		return repository.searchFAQByQuestion(question).stream().map(FAQConverter::toDTO).collect(Collectors.toList());
	}
}
