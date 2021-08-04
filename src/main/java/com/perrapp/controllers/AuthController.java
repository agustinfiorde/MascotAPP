package com.perrapp.controllers;

import static com.perrapp.utilidades.Constants.LOGIN;
import static com.perrapp.utilidades.Constants.REGISTRO;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perrapp.controllers.dto.ResponseDTO;
import com.perrapp.entidades.dto.UsuarioDTO;
import com.perrapp.errores.PerrappException;
import com.perrapp.jwt.AuthEntryPointJwt;
import com.perrapp.servicios.impl.UsuarioServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

	private AuthEntryPointJwt authEntryPointJwt;
	private UsuarioServiceImpl usuarioServiceImpl;

	@PostMapping(LOGIN)
	public ResponseEntity<ResponseDTO> login(HttpServletResponse res, @Valid @RequestBody UsuarioDTO usuario) {
		return ResponseEntity.ok(new ResponseDTO("token",authEntryPointJwt.authenticateUser(res, usuario), "Wachin Logueado!!!!"));
	}

	@PostMapping(REGISTRO)
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UsuarioDTO dto) throws PerrappException {
		return ResponseEntity.ok(new ResponseDTO("user",usuarioServiceImpl.save(dto), "El Wuachin se cargo bien"));
	}
}
