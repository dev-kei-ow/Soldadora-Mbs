package com.mycomp.service;

import java.util.List;

import com.mycomp.model.*;

public interface OrdenService {

	public List<Orden> findAll();

	public Orden getFindById(Integer id);

	public Orden save(Orden orden);

	public String generarNumeroOrden();

	List<Orden> findByidUsuario(Usuario idUsuario);

}
