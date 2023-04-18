package com.mycomp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycomp.model.*;
import com.mycomp.repository.*;

@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

	@Autowired
	private DetalleOrdenRepository detordrepo;

	@Override
	public DetalleOrden save(DetalleOrden detOrden) {

		return detordrepo.save(detOrden);
	}

}
