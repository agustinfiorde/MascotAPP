package com.perrapp.controllers;

import static com.perrapp.utilities.Constants.LIST;
import static com.perrapp.utilities.Constants.PET;
import static com.perrapp.utilities.Constants.SAVE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrapp.controllers.dto.ResponseDTO;
import com.perrapp.entities.dto.PetDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.services.impl.PetServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(PET)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PetController {

	private PetServiceImpl petService;

	@PostMapping(SAVE)
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody PetDTO dto) throws Exception {

		return ResponseEntity.ok(new ResponseDTO("pet", petService.save(dto), "El mascotin se cargo bien"));
	}
	
	@GetMapping(LIST)
	public ResponseEntity<ResponseDTO> listAll() throws MascotAppException {

		return ResponseEntity.ok(new ResponseDTO("pets", petService.getAll()));
	}

}
