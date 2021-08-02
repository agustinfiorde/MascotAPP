package com.perrapp.errores;

import java.util.UUID;

public class NotFoundException extends Exception {

    public <T> NotFoundException(Class<T> clazz, UUID id) {
        super(String.format("%s with id %s not found", clazz.getSimpleName(), id.toString()));
    }

}
