package com.infystore.ekart.exception;

/**
 * Thrown when the given user doesn't exist.
 */
public class NoSuchUserException extends EntityNotFoundException {
	
	public NoSuchUserException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
