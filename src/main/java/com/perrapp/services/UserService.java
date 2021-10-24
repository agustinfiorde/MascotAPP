package com.perrapp.services;

import com.perrapp.entities.dto.UserDTO;
import com.perrapp.errors.MascotAppException;

public interface UserService extends CRUDService<UserDTO> {

	public UserDTO findByEmail(String email);
	
	public UserDTO favoritePet(String userId, String petId) throws MascotAppException;
	
}
