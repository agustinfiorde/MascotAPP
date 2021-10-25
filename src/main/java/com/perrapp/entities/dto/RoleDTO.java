package com.perrapp.entities.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
@JsonInclude(Include.NON_NULL)
public final class RoleDTO {

	private static final long serialVersionUID = 4071780217025039863L;

	private String id;

	private String role;

}
