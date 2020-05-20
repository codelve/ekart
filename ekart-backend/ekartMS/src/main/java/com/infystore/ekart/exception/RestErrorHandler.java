package com.infystore.ekart.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infystore.ekart.dto.RestErrorResponse;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<Object> handleApplicationException(final ApplicationException ex) {
		return handleExceptionInternal(ex, BAD_REQUEST);
	}

	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<Object> handleEntityExistsException(final EntityAlreadyExistsException ex) {
		return handleExceptionInternal(ex, BAD_REQUEST);
	}

	@ExceptionHandler(EntityConflictsException.class)
	public ResponseEntity<Object> handleEntityConflictsException(final EntityConflictsException ex) {
		return handleExceptionInternal(ex, CONFLICT);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex) {
		return handleExceptionInternal(ex, NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(final Exception ex) {
		return handleExceptionInternal(ex, INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnsupportedOperationException.class)
	public ResponseEntity<Object> handleUnsupportedOperationException(final UnsupportedOperationException ex) {

		return handleExceptionInternal(ex, NOT_IMPLEMENTED);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		RestErrorResponse restErrorResponse = RestErrorResponse.of(status, ex);
		return super.handleExceptionInternal(ex, restErrorResponse, headers, status, request);
	}

	private ResponseEntity<Object> handleExceptionInternal(Exception ex, HttpStatus status) {
		return handleExceptionInternal(ex, null, null, status, null);
	}

}
