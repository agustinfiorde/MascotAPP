package com.perrapp.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/test")
public class zTestController {
	
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user-admin")
	@PreAuthorize("hasRole('ROLE_USUARIO') or hasRole('ROLE_ADMIN')")
	public String userAccess() {
		return "ambos Content.";
	}

	@GetMapping("/usuario")
	@PreAuthorize("hasRole('ROLE_USUARIO')")
	public String moderatorAccess() {
		return "User Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
