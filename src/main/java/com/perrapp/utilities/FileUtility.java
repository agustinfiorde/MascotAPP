package com.perrapp.utilities;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("FileUtility")
public class FileUtility {

	@Value("${fiordex.carpeta.fotos}")
	private String carpetaFotos;

	public void rootDirectory() {

		Path rootPath = Paths.get(carpetaFotos).toAbsolutePath();

		File folder = new File(rootPath.toString());

		if (!folder.isDirectory()) {
			folder.mkdir();
			System.out.println("Root folder 'web-files' was created succesfully");
		}
	}

	public Path personalDirectory(String id) {

		Path path = Paths.get(carpetaFotos + "/" + id).toAbsolutePath();

		File folder = new File(path.toString());

		if (!folder.isDirectory()) {
			folder.mkdir();
			System.out.println("Folder for " + id + " was created succesfully");
		}

		return path;
	}

}
