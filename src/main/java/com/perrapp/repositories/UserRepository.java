package com.perrapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.perrapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("SELECT a from User a WHERE a.email = :email")
	public User findByEmail(@Param("email")String email);

	@Query("SELECT a.password from User a WHERE a.id = :id")
	public String getPassById(@Param("id") String id);
	
}
