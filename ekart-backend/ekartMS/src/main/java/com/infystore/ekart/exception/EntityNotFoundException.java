package com.infystore.ekart.exception;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4653074055092167807L;
	
	public EntityNotFoundException(String message) {
		super(message);
	}
}
