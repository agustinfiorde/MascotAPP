package com.perrapp.entidades.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;

import com.perrapp.entidades.Usuario;
import com.perrapp.entidades.dto.UsuarioDTO;
import com.perrapp.servicios.impl.UsuarioServiceImpl;

public final class UsuarioConverter extends Converter<Usuario, UsuarioDTO> {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Override
	public UsuarioDTO entidadToDto(Usuario entity) {
		UsuarioDTO dto = modelMapper.map(entity, UsuarioDTO.class);
		return dto;
	}

	@Override
	public Usuario dtoToEntity(UsuarioDTO dto) throws ParseException {
		Usuario entity = modelMapper.map(dto, Usuario.class);
		return entity;
	}

}
