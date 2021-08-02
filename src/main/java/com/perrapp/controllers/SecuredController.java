package com.perrapp.controllers;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/secure")
@Slf4j
public class SecuredController {

	
	@GetMapping
	public ResponseEntity<?> reachSecureEndpoint() {
			
		return new ResponseEntity("If your are reading this you reached a secure endpoint", HttpStatus.OK);
	}
	
	public void asd () throws Exception{
		
	}
}