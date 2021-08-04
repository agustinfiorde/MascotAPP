package com.perrapp.controllers;

import static com.perrapp.utilidades.Constants.IMAGENES;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(IMAGENES)
public class ImagenController {

//	@PreAuthorize("hasRole('ROLE_USUARIO') or hasRole('ROLE_ADMIN')")
	
}
