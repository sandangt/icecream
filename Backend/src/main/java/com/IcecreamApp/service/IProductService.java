package com.IcecreamApp.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.IcecreamApp.DTO.ProductDTO;
import com.IcecreamApp.entity.Product;

public interface IProductService {

	Product create(ProductDTO productDTO);

	List<ProductDTO> readAll();

	Optional<ProductDTO> readById(long id);

	Optional<Product> update(long id, ProductDTO productDTO);

	boolean delete(long id);
	
	Map.Entry<Long, List<ProductDTO>> readByPage(int pageNumber, int pageSize);
	
	List<ProductDTO> searchProductsByName(String productname);
}
