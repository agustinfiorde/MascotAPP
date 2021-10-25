package com.perrapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perrapp.entities.Role;
import com.perrapp.enums.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	
	@Query("SELECT a from Role a WHERE a.role = :role ")
	public Optional<Role> searchByRol(RoleEnum role);
	
	@Query("SELECT r from User u JOIN u.roles r WHERE u.id LIKE :id")
	public List<Role> getRolesByUser(@Param("id") String id);
	
}
