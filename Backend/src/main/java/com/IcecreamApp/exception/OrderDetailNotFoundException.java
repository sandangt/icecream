package com.IcecreamApp.exception;

public class OrderDetailNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderDetailNotFoundException(Long id) {
			super("Could not find order detail " + id);
	}
}