package com.mycomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycomp.model.*;
import com.mycomp.repository.*;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

	@Autowired
	private TrabajadorRepository trabrepo;

	@Override
	@Transactional(readOnly = true)
	public List<Trabajador> findAll() {

		return trabrepo.findAll();
	}

	@Override
	@Transactional
	public Trabajador getFindById(Integer id) {

		return trabrepo.findById(id).get();
	}

	@Override
	@Transactional
	public Trabajador save(Trabajador trabajador) {

		return trabrepo.save(trabajador);
	}

	@Override
	@Transactional
	public void delete(Integer id) {

		trabrepo.deleteById(id);

	}

	@Override
	public void update(Integer id, Trabajador trabajador) {

		Trabajador trabajadorActual = trabrepo.findById(id).get();
		trabajadorActual.setNombre(trabajador.getNombre());
		trabajadorActual.setApellido(trabajador.getApellido());
		trabajadorActual.setEdad(trabajador.getEdad());
		trabajadorActual.setCorreo(trabajador.getCorreo());
		trabajadorActual.setCargo(trabajador.getCargo());
		trabajadorActual.setDni(trabajador.getDni());
		trabrepo.save(trabajadorActual);

	}

}
