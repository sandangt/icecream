package com.icecream.product.service;

import java.util.List;
import java.util.Optional;

import com.icecream.product.DTO.FeedbackDTO;
import com.icecream.product.DTO.PageDTO;
import com.icecream.product.DTO.ProductDTO;
import com.icecream.product.entity.Product;

public interface IProductService {

	Product create(ProductDTO productDTO);

	List<ProductDTO> readAll();

	Optional<ProductDTO> readById(long id);

	Optional<Product> update(long id, ProductDTO productDTO);

	boolean delete(long id);
	
	PageDTO<ProductDTO> readByPage(int pageNumber, int pageSize);
	
	List<ProductDTO> searchProductsByName(String productname);
	
	List<FeedbackDTO> readFeedbacksByProduct(long id);
}
