package com.perrapp.controllers;

import static com.perrapp.utilidades.Constants.GUARDAR;
import static com.perrapp.utilidades.Constants.USUARIOS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrapp.controllers.dto.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(USUARIOS)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioController {

	@PostMapping(GUARDAR)
	public ResponseEntity<ResponseDTO> signUp() {

        log.info("Anasd INFO Message");
        log.warn("Aasd WARN Message");
        log.error("Anasd ERROR Message");
		
		return ResponseEntity.ok(new ResponseDTO("asd"));
	}

}
