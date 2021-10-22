package com.perrapp.errors;

import org.springframework.http.HttpStatus;

public class AuthoritationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private Integer errorCode;

	public AuthoritationException(final String message, final HttpStatus status) {
		super(message);
		this.status = status;
		this.errorCode = status.value();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

}