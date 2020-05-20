package com.infystore.ekart.validator;

import com.infystore.ekart.exception.AccountTypeException;
import com.infystore.ekart.exception.FieldMissingException;
import com.infystore.ekart.exception.InvalidEmailException;
import com.infystore.ekart.exception.InvalidNameException;
import com.infystore.ekart.exception.InvalidPasswordException;

public interface Validator<T> {
	public boolean isValid(T obj) throws FieldMissingException, InvalidNameException, InvalidPasswordException, InvalidEmailException, AccountTypeException ;
}
