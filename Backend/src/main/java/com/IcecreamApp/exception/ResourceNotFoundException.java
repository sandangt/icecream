package com.IcecreamApp.exception;

public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String entityName, Long id) {
			super(String.format("Could not find %s %ld", entityName, id));
	}
}