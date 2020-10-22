package com.IcecreamApp.exception;

public class CategoryNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException(Long id) {
			super("Could not find category " + id);
	}
}