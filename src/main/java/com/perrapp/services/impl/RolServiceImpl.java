package com.perrapp.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrapp.entities.Role;
import com.perrapp.entities.converters.RoleConverter;
import com.perrapp.entities.dto.RoleDTO;
import com.perrapp.enums.RoleEnum;
import com.perrapp.repositories.RolRepository;
import com.perrapp.services.RolService;

@Service("RolService")
public class RolServiceImpl implements RolService {

	private RolRepository rolRepository;
	private RoleConverter rolConverter;

	@Autowired
	public RolServiceImpl(RolRepository rolRepository, RoleConverter rolConverter) {
		this.rolRepository = rolRepository;
		this.rolConverter = rolConverter;
	}

	@Override
	public void generateRoles() {
		if (getRoles().size() != RoleEnum.values().length) {
			for (RoleEnum rol : RoleEnum.values()) {
				rolRepository.save(new Role(rol));
			}
		}
	}

	@Override
	public List<RoleDTO> getRoles() {
		return rolConverter.entitiesToDto(rolRepository.findAll());
	}

	@Override
	public RoleDTO getRol(String rol) throws Exception {
		Optional<Role> entity = rolRepository.searchByRol(RoleEnum.valueOf(rol));
	
		if (entity.isPresent()) {
			return rolConverter.entityToDto(entity.get());
		}else {
			throw new Exception("No se encontro un rol asociado");
		}
	}

}
