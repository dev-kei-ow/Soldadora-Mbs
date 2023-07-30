package com.mycomp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	private String nombre;
	private String apellido;
	private String correo;
	private String pass;
	private String direccion;
	private String empresa;
	private String tipo;

	/* UN USUARIO CLIENTE - MUCHOS PRODUCTOS */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
	@JsonBackReference(value = "productos_usuario")
	private List<Producto> prodList;

	/* UN USUARIO CLIENTE - MUCHAS ORDENES */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.LAZY)
	@JsonBackReference(value = "ordenes_usuario")
	private List<Orden> ordenList;

	public Usuario() {

	}

	public Usuario(Integer idUsuario, String nombre, String apellido, String correo, String pass, String direccion,
			String empresa, String tipo) {

		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.pass = pass;
		this.direccion = direccion;
		this.empresa = empresa;
		this.tipo = tipo;

	}

	public Usuario(Integer idUsuario, String nombre, String apellido, String correo, String pass, String direccion,
			String empresa, String tipo, List<Producto> prodList, List<Orden> ordenList) {

		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.pass = pass;
		this.direccion = direccion;
		this.empresa = empresa;
		this.tipo = tipo;
		this.prodList = prodList;
		this.ordenList = ordenList;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Producto> getProdList() {
		return prodList;
	}

	public void setProdList(List<Producto> prodList) {
		this.prodList = prodList;
	}

	public List<Orden> getOrdenList() {
		return ordenList;
	}

	public void setOrdenList(List<Orden> ordenList) {
		this.ordenList = ordenList;
	}

}
