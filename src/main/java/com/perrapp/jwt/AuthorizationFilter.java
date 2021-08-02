package com.perrapp.jwt;

import static com.perrapp.utilidades.SecurityConstants.HEADER_NAME;
import static com.perrapp.utilidades.SecurityConstants.KEY;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.perrapp.errores.AuthenticationHandlerError;
import com.perrapp.errores.AuthoritationException;
import com.perrapp.servicios.impl.UsuarioServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	@Autowired
	private UsuarioServiceImpl applicationUserDetailsService;

	@Autowired
	public AuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			final ServletContext servletContext = request.getServletContext();
			final WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
			applicationUserDetailsService = webApplicationContext.getBean(UsuarioServiceImpl.class);
			final UsernamePasswordAuthenticationToken authentication = authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(request, response);
		} catch (AuthoritationException e) {
			AuthenticationHandlerError.getException(response, e.getStatus(), e.getErrorCode(), e.getMessage());
		}
	}

	private UsernamePasswordAuthenticationToken authenticate(final HttpServletRequest request) {
		final String token = request.getHeader(HEADER_NAME);

		if (token != null && !token.isEmpty()) {
			try {
				final Claims user = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(KEY.getBytes())).parseClaimsJws(token).getBody();

				final UserDetails applicationUser = applicationUserDetailsService.loadUserByUsername(user.getSubject());

				if (applicationUser != null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
				throw new AuthoritationException("User not authenticated", HttpStatus.UNAUTHORIZED);
			} catch (Exception e) {
				throw new AuthoritationException("Invalid token", HttpStatus.UNAUTHORIZED);
			}
		}
		throw new AuthoritationException("Bad authorization request", HttpStatus.BAD_REQUEST);
	}
}