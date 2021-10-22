package com.perrapp.entities.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

//	@NotBlank(message = "Name is mandatory")
	private String nickname;
	
//	@NotBlank(message = "Name is mandatory")
	private String petNumber;
	
//	@NotBlank(message = "Name is mandatory")
	private String breed;

	private LocalDate born;

	private String userId;
	
	private PictureDTO profilePicture;
	private String profilePictureB64;

	private List<PictureDTO> pictures = new ArrayList<PictureDTO>();

}
