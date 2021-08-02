package com.perrapp.controllers;

import static com.perrapp.utilidades.Constants.GUARDAR;
import static com.perrapp.utilidades.Constants.USUARIOS;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrapp.entidades.Usuario;
import com.perrapp.entidades.dto.ResponseDTO;
import com.perrapp.repositorios.RolRepository;
import com.perrapp.repositorios.UsuarioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(USUARIOS)
public class UsuarioController {

	private UsuarioRepository applicationUserRepository;
	private RolRepository rolRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UsuarioController(UsuarioRepository applicationUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder,RolRepository rolRepository) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.rolRepository = rolRepository;
	}

	@PostMapping(GUARDAR)
	public ResponseEntity<ResponseDTO> signUp(@Valid @RequestBody Usuario user) {

		Usuario usuario = new Usuario();
		usuario.setEmail(user.getEmail());
		usuario.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		System.out.println(applicationUserRepository.save(usuario));
		
        log.info("Anasd INFO Message");
        log.warn("Aasd WARN Message");
        log.error("Anasd ERROR Message");
		
		return new ResponseEntity(new ResponseDTO(usuario,200),HttpStatus.ACCEPTED);
	}

}
