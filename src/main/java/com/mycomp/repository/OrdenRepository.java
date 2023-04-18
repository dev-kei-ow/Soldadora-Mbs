package com.mycomp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.model.*;

public interface OrdenRepository extends JpaRepository<Orden, Integer> {

	List<Orden> findByidUsuario(Usuario idUsuario);

}
