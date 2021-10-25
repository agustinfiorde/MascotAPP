package com.perrapp.entities.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(Include.NON_NULL)
public final class UserDTO extends AuditDTO {

	private static final long serialVersionUID = 4071780217025039863L;

	private String id;

	private List<RoleDTO> roles;

	private String name;
	private String lastName;

	@NotBlank(message = "Email es necesario")
	private String email;

	private String password;

	private String dni;

	private List<PetDTO> pets;

	private String rolesSelected;

	private PetDTO favoritePet;

	private PictureDTO profilePicture;
	private String profilePictureB64;

}
