package com.mycomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.model.*;

public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {

}
