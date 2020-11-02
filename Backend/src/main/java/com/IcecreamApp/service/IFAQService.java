package com.IcecreamApp.service;

import java.util.List;
import java.util.Optional;

import com.IcecreamApp.DTO.FAQDTO;


public interface IFAQService {

	List<FAQDTO> readAll();

	Optional<FAQDTO> readById(long id);

	FAQDTO create(FAQDTO faqDTO);

	Optional<FAQDTO> update(long id, FAQDTO faqDTO);

	boolean delete(long id);
}
