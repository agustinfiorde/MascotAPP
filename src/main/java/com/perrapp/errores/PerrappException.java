package com.perrapp.errores;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public final class PerrappException extends Exception {

	private static final long serialVersionUID = 395023198858242963L;
	
	private final HttpStatus status;

    public PerrappException(String message) {
        super(message);
        status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public PerrappException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}