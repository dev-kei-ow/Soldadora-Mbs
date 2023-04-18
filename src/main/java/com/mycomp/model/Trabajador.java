package com.mycomp.model;

import java.io.Serializable;

import javax.persistence.*;;

@Entity
@Table(name = "trabajadores")
public class Trabajador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTrabajador;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private Integer edad;
	@Column
	private String correo;
	@Column
	private String cargo;
	@Column
	private Integer dni;

	public Trabajador() {

	}

	public Trabajador(Integer idTrabajador, String nombre, String apellido, Integer edad, String correo, String cargo,
			Integer dni) {

		this.idTrabajador = idTrabajador;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.correo = correo;
		this.cargo = cargo;
		this.dni = dni;
	}

	public Integer getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(Integer idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

}
