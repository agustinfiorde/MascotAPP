package com.perrapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perrapp.entities.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, String> {

	public Pet findByPetNumber(String petNumber);
	
	@Query("SELECT p from Pet p WHERE p.active IS TRUE")
	public List<Pet> findAllActives();
	
	@Query("SELECT p from User u JOIN u.pets p WHERE u.id LIKE :id AND p.active IS TRUE")
	public List<Pet> findAllByUser(@Param("id") String id);

	@Modifying
	@Query("update Pet p set p.active = FALSE WHERE p.id LIKE :id")
	void deactivatePet(@Param("id") String id);
	
	@Modifying
	@Query("update Pet p set p.active = TRUE WHERE p.id LIKE :id")
	void activatePet(@Param("id") String id);
	
}
