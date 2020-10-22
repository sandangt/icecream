package com.IcecreamApp.exception;

public class FAQNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FAQNotFoundException(Long id) {
			super("Could not find feedback " + id);
	}
}