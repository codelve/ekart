package com.infosys.infystore.ekart.validator;

import com.infosys.infystore.ekart.exception.AccountTypeException;
import com.infosys.infystore.ekart.exception.FieldMissingException;
import com.infosys.infystore.ekart.exception.InvalidEmailException;
import com.infosys.infystore.ekart.exception.InvalidNameException;
import com.infosys.infystore.ekart.exception.InvalidPasswordException;

public interface Validator<T> {
	public boolean isValid(T obj) throws FieldMissingException, InvalidNameException, InvalidPasswordException, InvalidEmailException, AccountTypeException ;
}
