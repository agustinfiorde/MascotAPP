package com.perrapp.entities.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
public final class PetDTO extends AuditDTO {

	private static final long serialVersionUID = 4071780217025039863L;

	private String id;

	@NotBlank(message = "Apodo es necesario")
	private String nickname;
	
	@NotBlank(message = "Pet Number es necesacio")
	private String petNumber;
	
	@NotBlank(message = "Raza es necesario")
	private String breed;

	@NotBlank(message = "Nacimiento es necesario")
	private LocalDate born;

	@NotBlank(message = "Dueño es necesario")
	private String userId;
	
	private PictureDTO profilePicture;
	private String profilePictureB64;

}
