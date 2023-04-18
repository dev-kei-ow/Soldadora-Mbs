package com.mycomp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycomp.model.*;
import com.mycomp.repository.*;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository prodrepo;

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {

		return prodrepo.findAll();
	}

	@Override
	@Transactional
	public Producto getFindById(Integer id) {

		return prodrepo.findById(id).get();
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {

		return prodrepo.save(producto);
	}

	@Override
	@Transactional
	public void delete(Integer id) {

		prodrepo.deleteById(id);

	}

	@Override
	public void update(Integer id, Producto producto) {

		Producto productoActual = prodrepo.findById(id).get();

		productoActual.setNombre(producto.getNombre());
		productoActual.setPrecio(producto.getPrecio());
		productoActual.setDescripcion(producto.getDescripcion());
		productoActual.setImagen(producto.getImagen());
		productoActual.setCantidad(producto.getCantidad());

		prodrepo.save(productoActual);

	}

}
