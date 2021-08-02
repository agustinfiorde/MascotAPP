package com.perrapp.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perrapp.entidades.Rol;
import com.perrapp.enums.RolEnum;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {
	
	@Query("SELECT a from Rol a WHERE a.rol = :rol ")
	public Optional<Rol> buscarPorRol(RolEnum rol);
	
}
