package com.perrapp.entities.converters;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.perrapp.entities.Picture;
import com.perrapp.entities.dto.PictureDTO;

@Component("PictureConverter")
public final class PictureConverter extends Converter<Picture, PictureDTO> {

	@Override
	public PictureDTO entityToDto(Picture entity) {
		PictureDTO dto = modelMapper.map(entity, PictureDTO.class);
		return dto;
	}

	@Override
	public Picture dtoToEntity(PictureDTO dto) throws ParseException {
		Picture entity = new Picture();
		entity = modelMapper.map(dto, Picture.class);
		return entity;
	}

}
