package com.perrapp.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class InfoResponse {

	private Integer count;
	private Object next;
	private Integer pages;
	private Object prev;

}
