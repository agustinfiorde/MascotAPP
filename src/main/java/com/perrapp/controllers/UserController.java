package com.perrapp.controllers;

import static com.perrapp.utilities.Constants.EDIT;
import static com.perrapp.utilities.Constants.FAV;
import static com.perrapp.utilities.Constants.LIST;
import static com.perrapp.utilities.Constants.USER;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrapp.controllers.dto.ResponseDTO;
import com.perrapp.entities.dto.UserDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.services.impl.UserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(USER)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

	private UserServiceImpl userService;

//	@GetMapping
//	public ResponseEntity<ResponseDTO> signUp() {
//		log.info("INFO Message");
//		log.warn("WARN Message");
//		log.error("ERROR Message");
//		return ResponseEntity.ok(new ResponseDTO("Testing Logs"));
//	}

	@GetMapping(LIST)
	public ResponseEntity<ResponseDTO> list() {
		return ResponseEntity.ok(new ResponseDTO("users", userService.getAll(), "Estos son los usuarios de la app"));
	}

	@PatchMapping(EDIT)
	public ResponseEntity<ResponseDTO> edit(@Valid @RequestBody UserDTO dto) throws MascotAppException {
		return ResponseEntity.ok(new ResponseDTO("user",userService.edit(dto), "El usuario se edito bien"));
	}
	
	@PatchMapping(FAV)
	public ResponseEntity<ResponseDTO> favoritePet(@PathVariable String userId,@PathVariable String petId) throws MascotAppException {
		userService.favoritePet(userId, petId);
		return ResponseEntity.ok(new ResponseDTO("Exito en asignacion del nuevo favorito"));
	}

}
