package com.IcecreamApp.exception;

public class FeedbackNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FeedbackNotFoundException(Long id) {
			super("Could not find feedback " + id);
	}
}