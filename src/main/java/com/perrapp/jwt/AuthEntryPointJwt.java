package com.perrapp.jwt;

import static com.perrapp.utilities.SecurityConstants.TOKEN_TYPE;

import java.io.IOException;

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
import com.perrapp.entities.dto.UserDTO;
import com.perrapp.services.impl.UserServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("AuthEntryPointJwt")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

	private UserServiceImpl userService;
	private AuthenticationManager authenticationManager;
	private JwtUtils jwtUtils;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
	}

	public Object authenticateUser(HttpServletResponse res, UserDTO usuario) {
		
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getPassword()));

		usuario = userService.findByEmail(usuario.getEmail());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtils.generateJwtToken(authentication);
		jwtUtils.setHeaderData(jwt, res);

		return new JwtResponse(jwt, jwtUtils.getJwtExpirationMs().toString(), TOKEN_TYPE, usuario);
	}

}