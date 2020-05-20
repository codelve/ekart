package com.infystore.ekart.dto;

import org.springframework.http.HttpStatus;

public class RestErrorResponse {

	private final int statusCode;
	private final String reasonPhrase;
	private final String message;

	public int getStatusCode() {
		return statusCode;
	}

	public String getReasonPhrase() {
		return reasonPhrase;
	}

	public String getMessage() {
		return message;
	}

	protected RestErrorResponse(HttpStatus status, String message) {
		statusCode = status.value();
		reasonPhrase = status.getReasonPhrase();
		this.message = message;
	}

	public static RestErrorResponse of(HttpStatus status) {
		return of(status, null);
	}

	public static RestErrorResponse of(HttpStatus status, Exception ex) {
		return new RestErrorResponse(status, ex.getMessage());
	}

}
