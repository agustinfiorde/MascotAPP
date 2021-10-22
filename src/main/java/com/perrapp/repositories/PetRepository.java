package com.perrapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perrapp.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

	public Pet findByPetNumber(String petNumber);
	
}
