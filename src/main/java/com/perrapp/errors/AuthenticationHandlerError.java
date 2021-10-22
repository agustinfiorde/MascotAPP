package com.perrapp.errors;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perrapp.controllers.dto.ResponseDTO;

public class AuthenticationHandlerError {

	public static ObjectMapper getException(final HttpServletResponse response, final HttpStatus status, final int code, final String message) throws JsonGenerationException, JsonMappingException, IOException {
		final ResponseDTO authenticationError = new ResponseDTO(message, code);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setStatus(status.value());
		response.setContentType("application/json");
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(response.getWriter(), authenticationError);
		return objectMapper;
	}
}
