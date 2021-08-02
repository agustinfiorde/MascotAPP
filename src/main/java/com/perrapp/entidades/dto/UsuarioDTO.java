package com.perrapp.entidades.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public final class UsuarioDTO extends AuditoriaDTO {

	private static final long serialVersionUID = 4071780217025039863L;

	private String id;

	private List<RolDTO> roles = new ArrayList<RolDTO>();

	private String nombre;
	private String apellido;

	private String email;

	private String dni;

	private ImagenDTO fotoPerfil;

}
