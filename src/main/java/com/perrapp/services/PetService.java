package com.perrapp.services;

import java.util.List;

import com.perrapp.entities.dto.PetDTO;

public interface PetService extends CRUDService<PetDTO> {

	public List<PetDTO> getAllByUser(String id);
	
}
