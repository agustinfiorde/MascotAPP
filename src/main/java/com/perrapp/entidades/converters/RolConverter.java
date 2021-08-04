package com.perrapp.entidades.converters;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.perrapp.entidades.Rol;
import com.perrapp.entidades.dto.RolDTO;
import com.perrapp.enums.RolEnum;
import com.perrapp.utilidades.StringUtilidad;

@Component("RolConverter")
public final class RolConverter extends Converter<Rol, RolDTO> {

	@Override
	public RolDTO entidadToDto(Rol entity) {
		RolDTO dto = modelMapper.map(entity, RolDTO.class);
		if (entity.getRol() != null) {
			dto.setRol(entity.getRol().name());
		}
		return dto;
	}

	@Override
	public Rol dtoToEntity(RolDTO dto) throws ParseException {
		
		Rol entity = modelMapper.map(dto, Rol.class);
		if (StringUtilidad.notNullEmpty(dto.getRol())) {
			entity.setRol(RolEnum.valueOf(dto.getRol()));
		}
		return entity;
	}

}
