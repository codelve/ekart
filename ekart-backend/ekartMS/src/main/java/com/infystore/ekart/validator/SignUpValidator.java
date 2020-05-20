package com.infystore.ekart.validator;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import com.infystore.ekart.dto.ERole;
import com.infystore.ekart.dto.SignUpDTO;
import com.infystore.ekart.exception.AccountTypeException;
import com.infystore.ekart.exception.FieldMissingException;
import com.infystore.ekart.exception.InvalidEmailException;
import com.infystore.ekart.exception.InvalidNameException;
import com.infystore.ekart.exception.InvalidPasswordException;

@Component
public class SignUpValidator implements Validator<SignUpDTO> {

	@Override
	public boolean isValid(SignUpDTO signUp) throws FieldMissingException, InvalidNameException,
			InvalidPasswordException, InvalidEmailException, AccountTypeException {

		if (isMadatoryFieldsPresent(signUp) && isValidName(signUp.getUsername())
				&& isValidConfirmPassword(signUp.getConfirmPassword(), signUp.getPassword())
				&& isValidEmail(signUp.getEmail()) && isValidPassword(signUp.getPassword())) {
			return true;
		}
		return false;
	}

	public boolean isValidName(String name) throws InvalidNameException {
		String regex = "[a-zA-Z]+";
		if (StringUtils.isBlank(name) || !Pattern.matches(regex, name)) {
			throw new InvalidNameException("Name contains invalid character");
		}
		return true;
	}

	public boolean isValidPassword(String password) throws InvalidPasswordException {
		String regex = "((?=.*[a-z])(?=.*\\\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,40})";
		if (!Pattern.matches(regex, password)) {
			throw new InvalidPasswordException(
					"Password should contain atleast a uppercase and a lower case , a number and a special character");
		}
		return true;
	}

	public boolean isValidConfirmPassword(String password, String confirmPassword) throws InvalidPasswordException {
		if (StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)
				|| !password.equals(confirmPassword)) {
			throw new InvalidPasswordException("Password and confirm password should be same");
		}
		return true;
	}

	public boolean isValidEmail(String email) throws InvalidEmailException {

		EmailValidator emailValidator = EmailValidator.getInstance();
		if (!emailValidator.isValid(email)) {
			throw new InvalidEmailException("Email should be valid");
		}
		return true;
	}

	public boolean isValidAccountType(String accountType) throws AccountTypeException {

		if (StringUtils.isBlank(accountType)) {
			throw new AccountTypeException("Account type cannot be empty");
		}
		ERole type = ERole.valueOf(accountType);
		if (type == null) {
			throw new AccountTypeException("Account type should be valid");
		}
		return true;
	}

	public boolean isMadatoryFieldsPresent(SignUpDTO signUp) throws FieldMissingException {
		if (StringUtils.isBlank(signUp.getUsername()) || StringUtils.isBlank(signUp.getEmail())
				|| StringUtils.isBlank(signUp.getPassword()) || StringUtils.isBlank(signUp.getConfirmPassword())) {
			throw new FieldMissingException("All the fields are mandatory");
		}
		return true;
	}

}
