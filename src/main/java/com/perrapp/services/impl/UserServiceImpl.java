package com.perrapp.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.perrapp.entities.Role;
import com.perrapp.entities.User;
import com.perrapp.entities.converters.UserConverter;
import com.perrapp.entities.dto.PictureDTO;
import com.perrapp.entities.dto.UserDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.repositories.PetRepository;
import com.perrapp.repositories.RoleRepository;
import com.perrapp.repositories.UserRepository;
import com.perrapp.services.PictureService;
import com.perrapp.services.RoleService;
import com.perrapp.services.UserService;
import com.perrapp.utilities.BASE64DecodedMultipartFile;
import com.perrapp.utilities.StringUtility;

import lombok.AllArgsConstructor;

@Service("UserService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, UserDetailsService {

	private RoleService rolServiceImpl;
	private UserRepository userRepository;
	private PetRepository petRepository;
	private RoleRepository roleRepository;
	private UserConverter usuarioConverter;
	private PictureService pictureService;

	@Override
	public UserDTO save(UserDTO d) throws MascotAppException {
		
		d.setRoles(rolServiceImpl.getRoles());

		validator(d);
		User e = userRepository.save(usuarioConverter.dtoToEntity(d));

		return usuarioConverter.entityToDto(e);
	}

	@Override
	public UserDTO edit(UserDTO d) throws MascotAppException, Exception {

		PictureDTO picture = pictureService.loadPicture(BASE64DecodedMultipartFile.base64ToMultipart(d.getProfilePictureB64()),
				d.getId());
		
		d.setProfilePicture(picture);
		
		User e = usuarioConverter.dtoToEntity(d);
		
		if (d.getPassword() == null) {
			e.setPassword(userRepository.getPassById(d.getId()));
		}
		
		if (d.getRoles() == null) {
			e.setRoles(roleRepository.getRolesByUser(d.getId()));
		}

		
		
		return usuarioConverter.entityToDto(userRepository.save(e));
	}

	@Override
	public UserDTO favoritePet(String userId, String petId) throws MascotAppException {
		User u = userRepository.getById(userId);
		u.setFavoritePet(petRepository.getById(petId));
		return usuarioConverter.entityToDto(userRepository.save(u));
	}

	@Override
	public UserDTO findByEmail(String email) {
		return usuarioConverter.entityToDto(userRepository.findByEmail(email));
	}

	@Override
	public List<UserDTO> getAll() {
		return usuarioConverter.entitiesToDto(userRepository.findAll());
	}

	@Override
	public void validator(UserDTO d) throws MascotAppException {
		
		if (userRepository.findByEmail(d.getEmail()) != null) {
			throw new MascotAppException("Error: Email is already in use!");
		}
		if (StringUtility.notNullEmpty(d.getEmail())) {
			throw new MascotAppException("Error: Email is necessary!");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);

		if (user != null && user.isActive()) {
			List<GrantedAuthority> permissions = new ArrayList<>();
			for (Role aux : user.getRoles()) {
				permissions.add(new SimpleGrantedAuthority("ROLE_" + aux.getRole().toString()));
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					permissions);
		}
		throw new UsernameNotFoundException("User Not Found with username: " + email);
	}

	@Override
	public UserDTO getOne(String id) {
		return usuarioConverter.entityToDto(userRepository.getById(id));
	}

	@Override
	public UserDTO deactivate(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> searchAll(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> searchAll(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllActives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getAllActives(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> searchAllActives(String q) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> searchAllActives(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO activate(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
