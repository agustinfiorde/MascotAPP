package com.perrapp.errors;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public final class MascotAppException extends Exception {

	private static final long serialVersionUID = 395023198858242963L;
	
	private final HttpStatus status;

    public MascotAppException(String message) {
        super(message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public MascotAppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}