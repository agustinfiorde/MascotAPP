package com.perrapp.entities.converters;

import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;

import com.perrapp.entities.Pet;
import com.perrapp.entities.dto.PetDTO;

@Component("PetConverter")
public final class PetConverter extends Converter<Pet, PetDTO> {

	@Override
	public PetDTO entityToDto(Pet entity) {
		return modelMapper.map(entity, PetDTO.class);
	}

	@Override
	public Pet dtoToEntity(PetDTO dto) throws ParseException {
		return modelMapper.map(dto, Pet.class);
	}

}
