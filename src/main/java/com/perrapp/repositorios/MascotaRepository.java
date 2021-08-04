package com.perrapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perrapp.entidades.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, String> {

	
}
