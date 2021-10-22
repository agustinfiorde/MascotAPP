package com.perrapp.controllers.dto;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseDTO {

	private InfoResponse info;
	private Map<String, Object> data;
	private String message;
	private Integer code;

	public ResponseDTO(String message) {
		this.message = message;
	}
	
	public ResponseDTO(String labelData, Object objectData) {
		this.data = Stream.of(new AbstractMap.SimpleImmutableEntry<>(labelData, objectData))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public ResponseDTO(String labelData, Object objectData, Integer code) {
		this.data = Stream.of(new AbstractMap.SimpleImmutableEntry<>(labelData, objectData))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		this.code = code;
	}

	public ResponseDTO(String labelData, Object objectData, String message) {
		this.data = Stream.of(new AbstractMap.SimpleImmutableEntry<>(labelData, objectData))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		this.message = message;
	}

	public ResponseDTO(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	public ResponseDTO(String labelData, Object objectData, String message, Integer code) {
		this.data = Stream.of(new AbstractMap.SimpleImmutableEntry<>(labelData, objectData))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		this.message = message;
		this.code = code;
	}
	
}
