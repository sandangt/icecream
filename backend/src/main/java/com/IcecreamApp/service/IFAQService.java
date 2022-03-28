package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.DTO.FAQDTO;
import com.IcecreamApp.DTO.PageDTO;
import com.IcecreamApp.entity.FAQ;


public interface IFAQService {

	List<FAQDTO> readAll();

	Optional<FAQDTO> readById(long id);

	FAQ create(FAQDTO faqDTO);

	Optional<FAQ> update(long id, FAQDTO faqDTO);

	boolean delete(long id);
	
	PageDTO<FAQDTO> readByPage(int pageNumber, int pageSize);
	
	List<FAQDTO> searchFAQByQuestion(String question);
}
