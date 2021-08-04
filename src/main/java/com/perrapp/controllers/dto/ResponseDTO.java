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
	private String mensaje;
	private Integer codigo;

	public ResponseDTO(String mensaje) {
		this.mensaje = mensaje;
	}

	public ResponseDTO(String labelData, Object objectData, Integer codigo) {
		this.data = Stream.of(new AbstractMap.SimpleImmutableEntry<>(labelData, objectData))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		this.codigo = codigo;
	}

	public ResponseDTO(String labelData, Object objectData, String mensaje) {
		this.data = Stream.of(new AbstractMap.SimpleImmutableEntry<>(labelData, objectData))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		this.mensaje = mensaje;
	}

	public ResponseDTO(String mensaje, Integer codigo) {
		this.mensaje = mensaje;
		this.codigo = codigo;
	}

	public ResponseDTO(String labelData, Object objectData, String mensaje, Integer codigo) {
		this.data = Stream.of(new AbstractMap.SimpleImmutableEntry<>(labelData, objectData))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		this.mensaje = mensaje;
		this.codigo = codigo;
	}
	
	public void setCodigo(Object codigoObj) {
		
	}
}
