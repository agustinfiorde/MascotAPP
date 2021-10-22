package com.perrapp.services;

import java.util.List;

import com.perrapp.entities.dto.RoleDTO;

public interface RolService {

	void generateRoles();

	List<RoleDTO> getRoles();

	RoleDTO getRol(String rol) throws Exception;

}
