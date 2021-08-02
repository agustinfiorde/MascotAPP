package com.perrapp.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.perrapp.entidades.Usuario;
import com.perrapp.repositorios.UsuarioRepository;

@Service("UsuarioService")
public final class UsuarioServiceImpl implements UserDetailsService {

	private UsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario applicationUser = usuarioRepository.buscarPorEmail(email);

		if (applicationUser == null) {
			throw new UsernameNotFoundException(email);
		} else {

			List<GrantedAuthority> permissions = new ArrayList<>();

//			for (Rol aux : applicationUser.getRoles()) {
//				permissions.add(new SimpleGrantedAuthority("ROLE_" + aux.getRol().toString()));
//			}

			return new User(applicationUser.getEmail(), applicationUser.getPassword(), permissions);
		}

	}

}
