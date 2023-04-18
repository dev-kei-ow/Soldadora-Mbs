package com.mycomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mycomp.model.*;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
	public Usuario findByEmail(String email);

}
