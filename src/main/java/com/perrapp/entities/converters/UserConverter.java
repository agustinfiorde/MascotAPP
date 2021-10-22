package com.perrapp.entities.converters;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.perrapp.entities.User;
import com.perrapp.entities.dto.UserDTO;

@Component("UserConverter")
public final class UserConverter extends Converter<User, UserDTO> {

	@Override
	public UserDTO entityToDto(User entity) {
		UserDTO dto = modelMapper.map(entity, UserDTO.class);
		dto.setPassword(null);
		dto.setCreatedDate(null);
		dto.setUpdatedDate(null);
		return dto;
	}

	@Override
	public User dtoToEntity(UserDTO dto) throws ParseException {
		User entity = new User();
		entity = modelMapper.map(dto, User.class);
		return entity;
	}

}
