package com.perrapp.errors;

import java.util.UUID;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1269084693014277856L;

	public <T> NotFoundException(Class<T> clazz, UUID id) {
        super(String.format("%s with id %s not found", clazz.getSimpleName(), id.toString()));
    }

}
