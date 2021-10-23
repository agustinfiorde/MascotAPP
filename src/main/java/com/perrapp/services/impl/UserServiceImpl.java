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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perrapp.entities.Role;
import com.perrapp.entities.User;
import com.perrapp.entities.converters.UserConverter;
import com.perrapp.entities.dto.UserDTO;
import com.perrapp.errors.MascotAppException;
import com.perrapp.repositories.PetRepository;
import com.perrapp.repositories.UserRepository;
import com.perrapp.services.CRUDService;
import com.perrapp.services.UserService;

import lombok.AllArgsConstructor;

@Service("UserService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService, CRUDService<UserDTO>, UserDetailsService {

	private RolServiceImpl rolServiceImpl;
	private UserRepository userRepository;
	private PetRepository petRepository;
	private UserConverter usuarioConverter;
	private PasswordEncoder encoder;

	@Override
	public UserDTO save(UserDTO d) throws MascotAppException {
		d.setPassword(encoder.encode(d.getPassword()));
		d.setRoles(rolServiceImpl.getRoles());

		validator(d);
		User e = userRepository.save(usuarioConverter.dtoToEntity(d));

		return usuarioConverter.entityToDto(e);
	}

	@Override
	public UserDTO edit(UserDTO d) throws MascotAppException {

		User e;
		
		if (d.getPassword().length() > 0) {
			d.setPassword(encoder.encode(d.getPassword()));
		}

		if (d.getPassword() == null) {
			e = userRepository.getById(d.getId());
			String pass = e.getPassword();
			d.setPassword(pass);
		}

		validator(d);
		e = userRepository.save(usuarioConverter.dtoToEntity(d));

		return usuarioConverter.entityToDto(e);
	}

	public UserDTO favoritePet(String userId, String petId) throws MascotAppException {
		User u = userRepository.getById(userId);
		u.setFavoritePet(petRepository.getById(petId));
		return usuarioConverter.entityToDto(userRepository.save(u));
	}

	@Override
	public UserDTO getOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDTO findByEmail(String email) {
		return usuarioConverter.entityToDto(userRepository.findByEmail(email));
	}

	@Override
	public List<UserDTO> getAll() {
		return usuarioConverter.entitiesToDto(userRepository.findAll());
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
	public void validator(UserDTO d) throws MascotAppException {
		if (userRepository.findByEmail(d.getEmail()) != null) {
			throw new MascotAppException("Error: Email is already in use!");
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

}
