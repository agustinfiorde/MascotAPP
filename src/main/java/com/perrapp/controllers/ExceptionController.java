package com.perrapp.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.perrapp.controllers.dto.ErrorResponse;
import com.perrapp.controllers.dto.ResponseDTO;
import com.perrapp.errores.AppException;
import com.perrapp.errores.NotFoundException;
import com.perrapp.errores.PerrappException;

@RestControllerAdvice
public class ExceptionController {

	
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsException(BadCredentialsException exception) {
    	return new ResponseEntity<ErrorResponse>(new ErrorResponse(500,exception.getMessage(),"Error en el usuario o password"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFoundException(NotFoundException exception) {
    	return new ResponseEntity<ResponseDTO>(new ResponseDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ResponseDTO> handleAppException(AppException exception) {
    	return new ResponseEntity<ResponseDTO>(new ResponseDTO(exception.getMessage()), exception.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ResponseDTO> errors = exception.getFieldErrors().stream()
                .map(error -> new ResponseDTO( error.getField(), error.getDefaultMessage() , error.getCode() ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(PerrappException.class)
    public ResponseEntity<ResponseDTO> handlePerrappException(PerrappException exception) {
    	return new ResponseEntity<ResponseDTO>(new ResponseDTO(exception.getMessage()), exception.getStatus());
    }

}
