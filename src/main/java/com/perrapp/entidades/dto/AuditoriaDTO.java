package com.perrapp.entidades.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AuditoriaDTO implements Serializable {

	protected static final long serialVersionUID = 1L;

	protected boolean activo;

	protected LocalDateTime alta;

	protected LocalDateTime edicion;

}
