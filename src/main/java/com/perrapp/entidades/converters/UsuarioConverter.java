package com.perrapp.entidades.converters;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.perrapp.entidades.Usuario;
import com.perrapp.entidades.dto.UsuarioDTO;

@Component("UsuarioConverter")
public final class UsuarioConverter extends Converter<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO entidadToDto(Usuario entity) {
		UsuarioDTO dto = modelMapper.map(entity, UsuarioDTO.class);
		dto.setPassword(null);
		return dto;
	}

	@Override
	public Usuario dtoToEntity(UsuarioDTO dto) throws ParseException {
		Usuario entity = new Usuario();
		entity = modelMapper.map(dto, Usuario.class);
		return entity;
	}

}
