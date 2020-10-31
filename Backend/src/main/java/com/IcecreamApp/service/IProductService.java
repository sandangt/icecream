package com.IcecreamApp.service;

import java.util.List;

import com.IcecreamApp.DTO.ProductDTO;

public interface IProductService {

	ProductDTO create(ProductDTO productDTO);

	List<ProductDTO> readAll();

	ProductDTO readById(long id);

	ProductDTO update(long id, ProductDTO productDTO);

	void delete(long id);


}
