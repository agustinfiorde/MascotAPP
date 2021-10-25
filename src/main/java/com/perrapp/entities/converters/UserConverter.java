package com.perrapp.entities.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.perrapp.entities.User;
import com.perrapp.entities.dto.UserDTO;

import lombok.AllArgsConstructor;

@Component("UserConverter")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class UserConverter extends Converter<User, UserDTO> {

	private PasswordEncoder encoder;
	
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
		if (dto.getPassword() != null) dto.setPassword(encoder.encode(dto.getPassword()));
		entity = modelMapper.map(dto, User.class);
		if (dto.getId() != null) entity.setId(dto.getId());
		return entity;
	}

}
