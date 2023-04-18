package com.mycomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomp.model.*;

public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {

}
