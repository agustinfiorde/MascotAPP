package com.perrapp.entities.converters;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.perrapp.entities.Role;
import com.perrapp.entities.dto.RoleDTO;
import com.perrapp.enums.RoleEnum;
import com.perrapp.utilities.StringUtility;

@Component("RolConverter")
public final class RoleConverter extends Converter<Role, RoleDTO> {

	@Override
	public RoleDTO entityToDto(Role entity) {
		RoleDTO dto = modelMapper.map(entity, RoleDTO.class);
		if (entity.getRole() != null) {
			dto.setRole(entity.getRole().name());
		}
		return dto;
	}

	@Override
	public Role dtoToEntity(RoleDTO dto) throws ParseException {
		
		Role entity = modelMapper.map(dto, Role.class);
		if (StringUtility.notNullEmpty(dto.getRole())) {
			entity.setRole(RoleEnum.valueOf(dto.getRole()));
		}
		return entity;
	}

}
