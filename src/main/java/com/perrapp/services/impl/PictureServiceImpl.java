package com.perrapp.services.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.perrapp.entities.Picture;
import com.perrapp.entities.converters.PictureConverter;
import com.perrapp.entities.dto.PictureDTO;
import com.perrapp.repositories.PictureRepository;
import com.perrapp.utilities.FileUtility;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service("PictureService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PictureServiceImpl {

	@Value("${fiordex.carpeta.fotos}")
	private String carpetaFotos;

	@NonNull
	private PictureRepository pictureRepository;

	@NonNull
	private FileUtility fileUtility;

	@NonNull
	private PictureConverter pictureConverter;

	public PictureDTO loadPicture(MultipartFile file, String id) throws Exception {

		try {

			String name = id + ".jpg";

			Path path = fileUtility.personalDirectory(id).resolve(name).toAbsolutePath();

			if (new File(path.toString()).exists()) {
				Files.delete(path);
				Files.copy(file.getInputStream(), path);
			} else {
				Files.copy(file.getInputStream(), path);

			}

			Picture entity = new Picture(id, path.toString(), name);
			
			return pictureConverter.entityToDto(pictureRepository.save(entity)) ;
		} catch (Exception e) {
			throw new Exception("Error al cargar la foto");
		}
	}

	public void delete(String id) throws Exception {

		Picture picture = pictureRepository.getOne(id);

		Path rootPath = Paths.get(carpetaFotos).resolve(picture.getName()).toAbsolutePath();

		File file = rootPath.toFile();

		if (file.exists() && file.canRead()) {
			if (file.delete()) {
				pictureRepository.delete(picture);
			}
		}
	}

}
