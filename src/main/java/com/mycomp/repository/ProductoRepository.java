package com.mycomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.model.*;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
