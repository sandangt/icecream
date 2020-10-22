package com.IcecreamApp.exception;

public class UserDetailNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserDetailNotFoundException(Long id) {
			super("Could not find user detail " + id);
	}
}