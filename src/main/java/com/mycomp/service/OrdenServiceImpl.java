package com.mycomp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.model.*;
import com.mycomp.repository.*;

@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	private OrdenRepository ordrepo;

	@Override
	public List<Orden> findAll() {

		return ordrepo.findAll();
	}

	@Override
	public Orden save(Orden orden) {

		return ordrepo.save(orden);
	}

	// 000010
	@Override
	public String generarNumeroOrden() {
		int numero = 0;
		String numConcat = "";

		List<Orden> ordenes = findAll();

		List<Integer> numeros = new ArrayList<Integer>();

		ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

		if (ordenes.isEmpty()) {

			numero = 1;

		} else {

			numero = numeros.stream().max(Integer::compare).get();
			numero++;

		}

		if (numero < 10) {// 00001

			numConcat = "0000" + String.valueOf(numero);

		} else if (numero < 100) {

			numConcat = "000" + String.valueOf(numero);

		} else if (numero < 1000) {

			numConcat = "00" + String.valueOf(numero);
		} else if (numero < 10000) {

			numConcat = "00" + String.valueOf(numero);
		}

		return numConcat;
	}

	@Override
	public List<Orden> findByidUsuario(Usuario idUsuario) {

		return ordrepo.findByidUsuario(idUsuario);
	}

	@Override
	public Orden getFindById(Integer id) {

		return ordrepo.findById(id).get();
	}

}
