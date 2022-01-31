package com.perrapp.controllers;

import static com.perrapp.utilities.Constants.ACTIVATE;
import static com.perrapp.utilities.Constants.CANCEL;
import static com.perrapp.utilities.Constants.GET_ID;
import static com.perrapp.utilities.Constants.LIST;
import static com.perrapp.utilities.Constants.LIST_ACTIVES;
import static com.perrapp.utilities.Constants.LIST_ID;
import static com.perrapp.utilities.Constants.PET;
import static com.perrapp.utilities.Constants.SAVE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrapp.controllers.dto.ResponseDTO;
import com.perrapp.entities.dto.PetDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.services.PetService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(PET)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PetController {

	private PetService petService;

	@PostMapping(SAVE)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody PetDTO dto) throws Exception {
		return ResponseEntity.ok(new ResponseDTO("pet", petService.save(dto), "El mascotin se cargo bien"));
	}

	@GetMapping(LIST)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> listAll() throws MascotAppException {
		return ResponseEntity.ok(new ResponseDTO("pets", petService.getAll()));
	}
	
	@GetMapping(GET_ID)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> get(@PathVariable String id) {
		return ResponseEntity.ok(new ResponseDTO("pet",  petService.getOne(id)));
	}
	
	@GetMapping(LIST_ACTIVES)
	public ResponseEntity<ResponseDTO> listAllActives() throws MascotAppException {
		return ResponseEntity.ok(new ResponseDTO("pets", petService.getAllActives()));
	}
	
	@GetMapping(LIST_ID)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> listByUser(@PathVariable String id) throws MascotAppException {
		return ResponseEntity.ok(new ResponseDTO("pets", petService.getAllByUser(id)));
	}

	@PatchMapping(CANCEL)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> delete(@PathVariable String id) throws MascotAppException {
		return ResponseEntity
				.ok(new ResponseDTO("Se dio de baja la mascota " + petService.deactivate(id).getNickname()));
	}
	
	@PatchMapping(ACTIVATE)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResponseDTO> activate(@PathVariable String id) throws MascotAppException {
		return ResponseEntity
				.ok(new ResponseDTO("Se dio de alta la mascota " + petService.activate(id).getNickname()));
	}

}
