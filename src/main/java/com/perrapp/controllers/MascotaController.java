package com.perrapp.controllers;

import static com.perrapp.utilidades.Constants.MASCOTAS;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(MASCOTAS)
public class MascotaController {

}
