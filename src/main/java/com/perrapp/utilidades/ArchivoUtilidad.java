package com.perrapp.utilidades;

import org.springframework.beans.factory.annotation.Value;

public class ArchivoUtilidad {

	@Value("${fiordex.carpeta.fotos}")
	private String carpetaFotos;
	
	public static void generarDirectorio(String nombre) {
		
	}
	
	public static void buscarDirectorio(String uri) {
		
	}
	
	public static boolean archivoIsPresent(String directorio, String nombre) {
		
		
		
		return false;
	}
	
}
