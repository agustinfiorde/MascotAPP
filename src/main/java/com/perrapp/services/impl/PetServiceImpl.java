package com.perrapp.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perrapp.entities.Pet;
import com.perrapp.entities.User;
import com.perrapp.entities.converters.PetConverter;
import com.perrapp.entities.dto.PetDTO;
import com.perrapp.entities.dto.PictureDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.repositories.PetRepository;
import com.perrapp.repositories.UserRepository;
import com.perrapp.utilities.BASE64DecodedMultipartFile;

import lombok.AllArgsConstructor;

@Service("PetService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PetServiceImpl {

	private PictureServiceImpl pictureService;
	private UserRepository userRepository;
	private PetRepository petRepository;
	private PetConverter petConverter;

	public PetDTO save(PetDTO d) throws Exception, MascotAppException {

		validator(d);
		
		if (d.getId() == null) d.setId(UUID.randomUUID().toString());
		
		PictureDTO picture = pictureService.loadPicture(BASE64DecodedMultipartFile.base64ToMultipart(d.getProfilePictureB64()),
				d.getId());
		
		d.setProfilePicture(picture);
		
		Optional<User> userOptional = userRepository.findById(d.getUserId());
		if (!userOptional.isPresent()) throw new MascotAppException("Error: No hay user con ese id");
		
		Pet pet = petRepository.save(petConverter.dtoToEntity(d));
		User user = userOptional.get();
		
		List<Pet> pets = new ArrayList<Pet>(user.getPets());
		pets.add(pet);
		
		user.setPets(pets);
		
		userRepository.save(user);
		return petConverter.entityToDto(pet);
	}
	
	public List<PetDTO> getAll() {
		return petConverter.entitiesToDto(petRepository.findAll());
	}
	
	public List<PetDTO> getAllActives() {
		return petConverter.entitiesToDto(petRepository.findAllActives());
	}
	
	public List<PetDTO> getAllByUser(String id) {
		return petConverter.entitiesToDto(petRepository.findAllByUser(id));
	}

	public PetDTO desactivatePet(String id) {
		petRepository.desactivatePet(id);
		return petConverter.entityToDto(petRepository.getById(id));
	}
	
	public void validator(PetDTO d) throws MascotAppException {
		if (petRepository.findByPetNumber(d.getPetNumber()) != null)
			throw new MascotAppException("Error: PetNumber is already in use!");

	}

}
