package com.perrapp.entidades.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public final class MascotaDTO extends AuditoriaDTO {

	private static final long serialVersionUID = 4071780217025039863L;

	private String id;

	private String apodo;
	private String numeroIdentificatorio;
	private String raza;

	private LocalDate nacimiento;

	private UsuarioDTO propietario;

	private ImagenDTO fotoPerfil;

	private List<ImagenDTO> fotos = new ArrayList<ImagenDTO>();

}
