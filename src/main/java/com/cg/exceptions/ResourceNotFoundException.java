package com.cg.exceptions;

public class ResourceNotFoundException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message,String id) {
		
		super(message+id);
	}


	
	
}
