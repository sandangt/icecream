package com.IcecreamApp.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.converter.FAQConverter;
import com.IcecreamApp.entity.FAQ;
import com.IcecreamApp.repository.FAQRepository;
import com.IcecreamApp.service.IFAQService;
import com.google.common.collect.Maps;

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
	public Map.Entry<Long, List<FAQDTO>> readByPage(int pageNumber, int pageSize) {
		Page<FAQ> pages = repository.findAll(PageRequest.of(pageNumber, pageSize));
		Long totalEntities = repository.count();
		return Maps.immutableEntry(totalEntities, pages.getContent().stream().map(FAQConverter::toDTO).collect(Collectors.toList()));
	}

}
