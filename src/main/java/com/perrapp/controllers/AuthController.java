package com.perrapp.controllers;

import static com.perrapp.utilities.Constants.LOGIN;
import static com.perrapp.utilities.Constants.REGISTER;

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
import com.perrapp.entities.dto.UserDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.jwt.AuthEntryPointJwt;
import com.perrapp.services.UserService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "**", maxAge = 3600)
@RestController
@RequestMapping
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthController {

	private AuthEntryPointJwt authEntryPointJwt;
	private UserService userService;

	@PostMapping(LOGIN)
	public ResponseEntity<ResponseDTO> login(HttpServletResponse res, @Valid @RequestBody UserDTO dto) {
		return ResponseEntity.ok(new ResponseDTO("token",authEntryPointJwt.authenticateUser(res, dto), "User Logueado"));
	}

	@PostMapping(REGISTER)
	public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO dto) throws Exception, MascotAppException {
		return ResponseEntity.ok(new ResponseDTO("user",userService.save(dto), "El Usuario se cargo bien"));
	}
}
