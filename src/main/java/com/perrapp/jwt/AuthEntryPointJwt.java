package com.perrapp.jwt;

import static com.perrapp.utilidades.SecurityConstants.TOKEN_TYPE;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.perrapp.controllers.dto.JwtResponse;
import com.perrapp.entidades.dto.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AuthEntryPointJwt")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}

	public Object authenticateUser(HttpServletResponse res, UsuarioDTO usuario) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);
		jwtUtils.setHeaderData(jwt, res);

		List<String> roles = authentication.getAuthorities().stream().map((e) -> e.getAuthority())
				.collect(Collectors.toList());

		return new JwtResponse(jwt, jwtUtils.getJwtExpirationMs().toString(), TOKEN_TYPE, usuario.getId(),
				usuario.getEmail(), roles);
	}

}
