package com.mycomp.service;

import java.util.List;

import com.mycomp.model.*;

public interface ProductoService {

	public List<Producto> findAll();

	public Producto getFindById(Integer id);

	public Producto save(Producto producto);

	public void delete(Integer id);

	public void update(Integer id, Producto producto);

}
