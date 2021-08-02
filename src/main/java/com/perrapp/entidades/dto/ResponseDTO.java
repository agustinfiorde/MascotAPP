package com.perrapp.entidades.dto;

import lombok.Data;

@Data
public class ResponseDTO {

	private Object data;
	private String mensaje;
	private Object codigo;

	public ResponseDTO(String mensaje) {
		this.mensaje = mensaje;
	}

	public ResponseDTO(Object data, Integer codigo) {
		this.data = data;
		this.codigo = codigo;
	}

	public ResponseDTO(String mensaje, Integer codigo) {
		this.mensaje = mensaje;
		this.codigo = codigo;
	}

	public ResponseDTO(Object data, String mensaje, Object codigo) {
		this.data = data;
		this.mensaje = mensaje;
		this.codigo = codigo;
	}

}
