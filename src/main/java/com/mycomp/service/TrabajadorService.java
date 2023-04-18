package com.mycomp.service;

import java.util.List;

import com.mycomp.model.*;

public interface TrabajadorService {

	public List<Trabajador> findAll();

	public Trabajador getFindById(Integer id);

	public Trabajador save(Trabajador trabajador);

	public void update(Integer id, Trabajador trabajador);

	public void delete(Integer id);

}
