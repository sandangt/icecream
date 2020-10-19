package com.IcecreamApp.service;

import org.springframework.stereotype.Service;

import com.IcecreamApp.entity.Category;
import com.IcecreamApp.repository.CategoryRepository;

@Service
public class CategoryService extends GeneralService<Category, CategoryRepository> {

	public CategoryService(CategoryRepository repository) {
		super(repository);
	}
	
}