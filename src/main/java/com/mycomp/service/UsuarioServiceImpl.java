package com.mycomp.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.model.*;
import com.mycomp.repository.*;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usurepo;

	@Override
	public List<Usuario> findAll() {

		return usurepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario getFindById(Integer id) {

		return usurepo.findById(id).orElse(null);
	}

	@Override
	public Usuario save(Usuario usuario) {

		return usurepo.save(usuario);
	}

	@Override
	public void update(Integer id, Usuario usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario getFindByEmail(String email) {

		return usurepo.findByEmail(email);
	}

}
