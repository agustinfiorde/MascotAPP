package com.perrapp.servicios.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrapp.entidades.Rol;
import com.perrapp.entidades.converters.RolConverter;
import com.perrapp.entidades.dto.RolDTO;
import com.perrapp.enums.RolEnum;
import com.perrapp.repositorios.RolRepository;
import com.perrapp.servicios.RolService;

@Service("RolService")
public final class RolServiceImpl implements RolService {

	private RolRepository rolRepository;
	private RolConverter rolConverter;

	@Autowired
	public RolServiceImpl(RolRepository rolRepository, RolConverter rolConverter) {
		this.rolRepository = rolRepository;
		this.rolConverter = rolConverter;
	}

	@Override
	public void generarRoles() {
		if (obtenerRoles().size() != RolEnum.values().length) {
			for (RolEnum rol : RolEnum.values()) {
				rolRepository.save(new Rol(rol));
			}
		}
	}

	@Override
	public List<RolDTO> obtenerRoles() {
		return rolConverter.entidadesToDto(rolRepository.findAll());
	}

	@Override
	public RolDTO obtenerRol(String rol) throws Exception {
		Optional<Rol> entity = rolRepository.buscarPorRol(RolEnum.valueOf(rol));
	
		if (entity.isPresent()) {
			return rolConverter.entidadToDto(entity.get());
		}else {
			throw new Exception("No se encontro un rol asociado");
		}
	}

}
