package com.perrapp.controllers;

import static com.perrapp.utilities.Constants.PICTURE;
import static com.perrapp.utilities.Constants.SAVE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perrapp.controllers.dto.ResponseDTO;
import com.perrapp.entities.dto.PetDTO;
import com.perrapp.services.impl.PictureServiceImpl;
import com.perrapp.utilities.BASE64DecodedMultipartFile;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(PICTURE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PictureController implements Controller {

	private PictureServiceImpl pictureService;

//	@PostMapping(SAVE + "/{id}")
//	public ResponseEntity<ResponseDTO> guardarImagen(@PathVariable String id,
//			@RequestParam MultipartFile profilePicture) throws Exception {
//
//		pictureService.loadPicture(profilePicture, id);
//
//		return ResponseEntity.ok(new ResponseDTO("Se guardo re piola"));
//	}

	@PostMapping(SAVE + "/{id}")
	public ResponseEntity<ResponseDTO> save(@PathVariable String id, @RequestBody PetDTO pet) throws Exception {


		pictureService.loadPicture(BASE64DecodedMultipartFile.base64ToMultipart(pet.getProfilePictureB64()),
				id);

		return ResponseEntity.ok(new ResponseDTO("Se guardo re piola"));
	}

}
