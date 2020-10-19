package com.IcecreamApp.service;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Product;
import com.IcecreamApp.repository.ProductRepository;

@Service
public class ProductService extends GeneralService<Product, ProductRepository> {

	public ProductService(ProductRepository repository) {
		super(repository);
	}
}
