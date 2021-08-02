package com.perrapp.entidades.converters;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.perrapp.entidades.Rol;
import com.perrapp.entidades.dto.RolDTO;

@Component("RolConverter")
public final class RolConverter extends Converter<Rol, RolDTO> {

	@Override
	public RolDTO entidadToDto(Rol entity) {
		RolDTO dto = modelMapper.map(entity, RolDTO.class);
		return dto;
	}

	@Override
	public Rol dtoToEntity(RolDTO dto) throws ParseException {
		Rol entity = modelMapper.map(dto, Rol.class);
		return entity;
	}

}
