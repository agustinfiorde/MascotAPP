package com.perrapp.services;

import java.util.List;

import com.perrapp.entities.dto.RoleDTO;

public interface RoleService {

	void generateRoles();

	List<RoleDTO> getRoles();

	RoleDTO getRol(String rol) throws Exception;

}
