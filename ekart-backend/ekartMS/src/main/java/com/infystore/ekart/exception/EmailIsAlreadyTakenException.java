package com.infystore.ekart.exception;

/**
 * Thrown when the desired email address is already taken.
 */
public class EmailIsAlreadyTakenException extends EntityAlreadyExistsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5596849576876782191L;

	public EmailIsAlreadyTakenException(String string) {
		super(string);
	}
}
