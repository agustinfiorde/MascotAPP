package com.perrapp.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrapp.entities.Pet;
import com.perrapp.entities.converters.PetConverter;
import com.perrapp.entities.dto.PetDTO;
import com.perrapp.entities.dto.PictureDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.repositories.PetRepository;
import com.perrapp.utilities.BASE64DecodedMultipartFile;

import lombok.AllArgsConstructor;

@Service("PetService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PetServiceImpl {

	private PictureServiceImpl pictureService;
	private PetRepository petRepository;
	private PetConverter petConverter;

	public PetDTO save(PetDTO d) throws Exception {

		validator(d);
		
		if (d.getId() == null) d.setId(UUID.randomUUID().toString());;
		
		PictureDTO p = pictureService.loadPicture(BASE64DecodedMultipartFile.base64ToMultipart(d.getProfilePictureB64()),
				d.getId());
		
		d.setProfilePicture(p);
		
		Pet e = petRepository.save(petConverter.dtoToEntity(d));
		
		

		return petConverter.entityToDto(e);
	}
	
	public List<PetDTO> getAll() {
		return petConverter.entitiesToDto(petRepository.findAll());
	}

	public void validator(PetDTO d) throws MascotAppException {
		if (petRepository.findByPetNumber(d.getPetNumber()) != null)
			throw new MascotAppException("Error: PetNumber is already in use!");

	}

}
