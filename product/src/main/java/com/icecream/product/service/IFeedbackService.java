package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.FeedbackDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.entity.Feedback;

public interface IFeedbackService {

	List<FeedbackDTO> readAll();

	Optional<FeedbackDTO> readById(long id);
	
	Feedback create(FeedbackDTO feedbackDTO);

	Optional<Feedback> update(long id, FeedbackDTO feedbackDTO);

	boolean delete(long id);

	PageDTO<FeedbackDTO> readByPage(int pageNumber, int pageSize);
	
}
