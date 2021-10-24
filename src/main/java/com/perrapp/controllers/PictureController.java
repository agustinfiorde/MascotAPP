package com.perrapp.controllers;

import static com.perrapp.utilities.Constants.PICTURE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(PICTURE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PictureController implements Controller {

//	private PictureService pictureService;

//	@PostMapping(SAVE + "/{id}")
//	public ResponseEntity<ResponseDTO> guardarImagen(@PathVariable String id,
//			@RequestParam MultipartFile profilePicture) throws Exception {
//
//		pictureService.loadPicture(profilePicture, id);
//
//		return ResponseEntity.ok(new ResponseDTO("Se guardo re piola"));
//	}

//	@PostMapping(SAVE + "/{id}")
//	public ResponseEntity<ResponseDTO> save(@PathVariable String id, @RequestBody PetDTO pet) throws Exception {
//
//
//		pictureService.loadPicture(BASE64DecodedMultipartFile.base64ToMultipart(pet.getProfilePictureB64()),
//				id);
//
//		return ResponseEntity.ok(new ResponseDTO("Se guardo re piola"));
//	}

}
