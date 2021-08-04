package com.perrapp.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.perrapp.entidades.Rol;
import com.perrapp.entidades.Usuario;
import com.perrapp.entidades.converters.UsuarioConverter;
import com.perrapp.entidades.dto.UsuarioDTO;
import com.perrapp.errores.PerrappException;
import com.perrapp.repositorios.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service("UsuarioService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public final class UsuarioServiceImpl implements UserDetailsService {

	private RolServiceImpl rolServiceImpl;
	private UsuarioRepository usuarioRepository;
	private UsuarioConverter usuarioConverter;
	private PasswordEncoder encoder;

	public UsuarioDTO save(UsuarioDTO d) throws PerrappException {

		d.setPassword(encoder.encode(d.getPassword()));
		d.setRoles(rolServiceImpl.obtenerRoles());

		validation(d);
		Usuario e = usuarioRepository.save(usuarioConverter.dtoToEntity(d));

		return usuarioConverter.entidadToDto(e);
	}

	public void validation(UsuarioDTO d) throws PerrappException {

		if (usuarioRepository.buscarPorEmail(d.getEmail()) != null) {
			throw new PerrappException("Error: Email is already in use!");
		}

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario user = usuarioRepository.buscarPorEmail(email);

		if (user != null && user.isActivo()) {
			List<GrantedAuthority> permissions = new ArrayList<>();
			for (Rol aux : user.getRoles()) {
				permissions.add(new SimpleGrantedAuthority("ROLE_" + aux.getRol().toString()));
			}
			return new User(user.getEmail(), user.getPassword(), permissions);
		}
		throw new UsernameNotFoundException("User Not Found with username: " + email);
	}

}
