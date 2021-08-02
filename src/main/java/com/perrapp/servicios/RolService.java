package com.perrapp.servicios;

import java.util.List;

import com.perrapp.entidades.dto.RolDTO;

public interface RolService {

	void generarRoles();

	List<RolDTO> obtenerRoles();

	RolDTO obtenerRol(String rol) throws Exception;

}
