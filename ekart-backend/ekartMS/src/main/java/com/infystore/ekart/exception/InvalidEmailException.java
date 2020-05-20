package com.infystore.ekart.exception;

/**
 * Thrown when an invalid email address is encountered.
 */
public class InvalidEmailException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmailException(String string) {
		super(string);
	}
}
