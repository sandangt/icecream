package com.IcecreamApp.exception;

public class RoleNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RoleNotFoundException(Long id) {
			super("Could not find role " + id);
	}
}