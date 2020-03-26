package com.infosys.infystore.ekart.exception;

public class UserNameAlreadyTakenException extends EntityAlreadyExistsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNameAlreadyTakenException(String string) {
		super(string);
	}
}