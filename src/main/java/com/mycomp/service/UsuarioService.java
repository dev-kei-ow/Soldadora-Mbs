package com.mycomp.service;

import java.util.List;

import com.mycomp.model.*;

public interface UsuarioService {

	public List<Usuario> findAll();

	public Usuario getFindById(Integer id);

	public Usuario getFindByEmail(String email);

	public Usuario save(Usuario usuario);

	public void update(Integer id, Usuario usuario);

	public void delete(Integer id);
}
