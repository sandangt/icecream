package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.FAQDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.entity.FAQ;


public interface IFAQService {

	List<FAQDTO> readAll();

	Optional<FAQDTO> readById(long id);

	FAQ create(FAQDTO faqDTO);

	Optional<FAQ> update(long id, FAQDTO faqDTO);

	boolean delete(long id);
	
	PageDTO<FAQDTO> readByPage(int pageNumber, int pageSize);
	
	List<FAQDTO> searchFAQByQuestion(String question);
}
