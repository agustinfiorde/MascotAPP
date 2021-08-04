package com.perrapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perrapp.entidades.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, String> {
	

}
