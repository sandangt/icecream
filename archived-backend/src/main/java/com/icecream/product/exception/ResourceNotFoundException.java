package com.icecream.product.exception;

public class ResourceNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String entityName, Long id) {
			super(String.format("Could not find %s %d", entityName, id));
	}

	public ResourceNotFoundException(String message) {
			super(message);
	}
}
