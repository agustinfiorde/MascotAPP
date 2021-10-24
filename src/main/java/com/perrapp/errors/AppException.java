package com.perrapp.errors;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final HttpStatus status;

    public AppException(String message) {
        super(message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
