package com.mycomp.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.mycomp.model.*;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService ususrvc;

	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = ususrvc.getFindByEmail(username);

		if (usuario != null) {

			session.setAttribute("idusuario", usuario.getIdUsuario());

			Usuario user = usuario;

			return User.builder()
					.username(user.getNombre())
					.password(user.getPass())
					.roles(user.getTipo()).build();

		} else {

			throw new UsernameNotFoundException("Usuario no encontrado");

		}
	}

}
