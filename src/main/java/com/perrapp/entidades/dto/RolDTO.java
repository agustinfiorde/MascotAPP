package com.perrapp.entidades.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public final class RolDTO extends AuditoriaDTO {

	private static final long serialVersionUID = 4071780217025039863L;

	private String id;

	private RolDTO rol;

}
