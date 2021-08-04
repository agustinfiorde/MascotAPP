package com.perrapp.controllers.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public final class JwtResponse {
	
	private String accessToken;
	private String exipresIn;
	private String type;
	
	private String id;
	private String email;
	private List<String> roles;
	
}
